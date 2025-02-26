<script setup lang="ts">
import { nextTick, onMounted, reactive, ref, watch } from 'vue'
import { showConfirmDialog, showDialog } from 'vant'
import type { FormInstance } from 'vant'
import AuditResult from '@/components/AuditResult.vue'
import { AuditStatus } from '@/utils/enum'
import { Log } from '@/utils'
import _ from 'lodash'
import { uploadBatch } from '@/api/upload'
import { addVisitorInfo, type VisitorInfoDomain } from '@/api/visitor'
import dayjs from 'dayjs'

const active = ref(0)
const step = ref(0)
const isFinished = ref(false)
const formRef = ref<FormInstance | null>(null)

// 切换tab页
function onClickTab({ title, disabled }: { title: string; disabled: boolean }) {
  if (disabled) {
    Log.info('请先按照要求填写申请信息!')
  }
}

// 访客的信息
const visitorInfo = reactive<VisitorInfo>({
  visitorName: '',
  phone: '',
  idCard: '',
  visitorUnit: '',
  visitReason: '',
  cardImages: [],
  faceImage: [],
  visitTime: '',
  visitStartTime: '',
  visitEndTime: ''
})


// 选择时间确认
// 时间展示用
const dateValue = ref('')
const showPicker = ref(false)

// 日期选择
const startDate = ref(['2022', '06', '01'])
const endDate = ref(['2023', '06', '01'])

const minDate = new Date(2020, 0, 1)
const maxDate = new Date(2025, 5, 1)

function handleChooseDuration() {
  showPicker.value = true
}

const onConfirmRange = () => {
  visitorInfo.visitStartTime = `${startDate.value.join('/')}`
  visitorInfo.visitEndTime = `${endDate.value.join('/')}`
  dateValue.value = `${startDate.value.join('/')} 至 ${endDate.value.join('/')}`
  showPicker.value = false
}

const onCancel = () => {
  showPicker.value = false
}

// 提交表单 调度弹窗选择审核人
const auditShow = ref(false)

async function handleSubmit() {
  if (formRef.value) {
    formRef.value.validate('user').then(() => {
      // 发送请求 然后执行相关操作
      auditShow.value = true
      active.value = 1
      step.value++
    })
  }
}

// 审核人选择
const auditInfo = reactive<AuditInfo>({
  id: 0,
  name: '',
  status: AuditStatus.AUDIT_PASS.text
})


const auditChooseShow = ref(false)
const columns: AuditColumn[] = [
  { text: '张明测试1', value: 1 },
  { text: '张明测试2', value: 2 },
  { text: '张明测试3', value: 2 }
]
const auditPickerValue = ref<string[]>([])
const onConfirmAudit = ({
                          selectedValues,
                          selectedOptions
                        }: {
  selectedValues: []
  selectedOptions: [{ text: string; value: number }]
}) => {
  auditInfo.name = selectedOptions[0]?.text
  auditInfo.id = selectedOptions[0]?.value
  auditPickerValue.value = selectedValues
  auditChooseShow.value = false
}

watch(
  () => active.value,
  () => {
    if (active.value === 0) {
      showConfirmDialog({
        title: '提示',
        message: '是否要重新填写访客申请信息?'
      }).then(() => {
        visitorInfo.visitorName = ''
        visitorInfo.phone = ''
        visitorInfo.idCard = ''
        visitorInfo.visitorUnit = ''
        visitorInfo.visitReason = ''
        visitorInfo.cardImages = []
        visitorInfo.faceImage = []
        visitorInfo.visitTime = ''
        visitorInfo.visitStartTime = ''
        visitorInfo.visitEndTime = ''
      })
    }
  },
  {
    immediate: false
  }
)

onMounted(() => {
  Log.info('请按照要求填写申请信息!')

  // 获取审核人的信息 这里后端 给访客 和 审核人 设置为一个部门 他们可以互相具有查看权限
  // 这样 每次新注册的用户 默认分配到和审核人在一个部门
  // 审核人默认都在 访客和审核人范围 这个部门

})

// 提交表单
const onSubmit = () => {
  // 数据库存值 审核状态为审核中
  const files = _.concat(visitorInfo.cardImages, visitorInfo.faceImage)
  const fileList = files.map((item: any) => {
    return item.file
  })
  // 先调用批量上传接口 把图片存到服务器 拿到地址
  uploadBatch(fileList).then((res: { msg: string, urls: string, code: number, } & unknown | undefined) => {
    // 拿到urls以后 分割一下
    let urls: string[] = res?.urls.split(',') as string[]
    // 前两项是身份证正反面 然后最后一张是人脸照片
    visitorInfo.cardImages = urls.slice(0, 2)
    visitorInfo.faceImage = urls.slice(2, 3)

    // 拿到开始访客时间 和结束访客时间 计算差额
    const startTime = dayjs(visitorInfo.visitStartTime).format('YYYY-MM-DD HH:mm:ss')
    const endTime = dayjs(visitorInfo.visitEndTime)

    // 计算差额
    const duration = endTime.diff(dayjs(startTime), 'day')
    const body: VisitorInfoDomain = {
      name: visitorInfo.visitorName,
      phone: visitorInfo.phone,
      idCard: visitorInfo.idCard,
      visitorUnit: visitorInfo.visitorUnit,
      visitReason: visitorInfo.visitReason,
      idCardFrontImage: visitorInfo.cardImages[0],
      idCardBackImage: visitorInfo.cardImages[1],
      faceImage: visitorInfo.faceImage[0],
      visitTime: startTime,
      visitDuration: duration,
      requestTime: dayjs().format('YYYY-MM-DD HH:mm:ss'),
      status: AuditStatus.UNAUDITED.text
    }
    // 然后调用添加访客信息的接口
    addVisitorInfo(body).then(res => {
      Log.info('添加访客信息成功')
      // 先解除禁用
      isFinished.value = true
      nextTick(() => {
        // 展示用户的申请信息
        active.value = 1
        step.value = 1
      })
    })
  })
}

</script>

<template>
  <div>
    <van-steps :active="step" active-icon="success" active-color="#1989fa">
      <van-step>填写申请信息</van-step>
      <van-step>申请进度</van-step>
      <van-step>查看审核结果</van-step>
    </van-steps>
    <van-tabs v-model:active="active" @click-tab="onClickTab">
      <van-tab title="填写申请信息">
        <van-form @submit="onSubmit" ref="form" name="user">
          <van-cell-group inset>
            <van-field
              v-model="visitorInfo.visitorName"
              name="姓名"
              label="姓名"
              placeholder="姓名"
              :rules="[{ required: true, message: '请填写姓名' }]"
            />
            <van-field
              v-model="visitorInfo.phone"
              type="number"
              name="手机号"
              label="手机号"
              placeholder="手机号"
              :rules="[
                { required: true, message: '请填写手机号' },
                { pattern: /^1[3-9]\d{9}$/, message: '手机号格式不正确' }
              ]"
            />
            <van-field
              v-model="visitorInfo.idCard"
              type="number"
              name="身份证号"
              label="身份证号"
              placeholder="身份证号"
              :rules="[
                { required: true, message: '请填写身份证号' },
                {
                  pattern: /^[1-9]\d{5}(18|19|20)\d{2}\d{2}\d{2}\d{3}[0-9Xx]$/,
                  message: '身份证号格式不正确'
                }
              ]"
            />
            <van-field
              v-model="visitorInfo.visitorUnit"
              name="访客单位"
              label="访客单位"
              placeholder="访客单位"
              :rules="[{ required: true, message: '请填写访客单位' }]"
            />
            <van-field
              v-model="visitorInfo.visitReason"
              name="到访理由"
              label="到访理由"
              placeholder="访客单位"
              :rules="[{ required: true, message: '请填写到访理由' }]"
            />
            <van-field name="cardImages" label="身份证正反面照片">
              <template #input>
                <van-uploader
                  v-model="visitorInfo.cardImages as any"
                  :max-count="2"
                  :rules="[{ required: true, message: '请上传身份证正反面照片' }]"
                />
              </template>
            </van-field>
            <van-field name="faceImage" label="人脸照片">
              <template #input>
                <van-uploader
                  v-model="visitorInfo.faceImage as any"
                  :max-count="1"
                  :rules="[{ required: true, message: '请上传人脸照片' }]"
                />
              </template>
            </van-field>

            <!-- 选择日期范围 -->
            <van-field
              is-link
              readonly
              name="datePicker"
              v-model="dateValue"
              label="访客时间"
              placeholder="点击选择访客时间"
              :rules="[{ required: true, message: '请选择访客时间' }]"
              @click="handleChooseDuration"
            />
            <van-popup v-model:show="showPicker" destroy-on-close position="bottom">
              <!-- 时间范围 -->
              <van-picker-group
                title="预约日期"
                :tabs="['开始日期', '结束日期']"
                @confirm="onConfirmRange"
                @cancel="onCancel"
              >
                <van-date-picker v-model="startDate" :min-date="minDate" :max-date="maxDate" />
                <van-date-picker v-model="endDate" :min-date="minDate" :max-date="maxDate" />
              </van-picker-group>
            </van-popup>

            <!-- 审核人选择 -->
            <van-field
              v-model="auditInfo.name"
              is-link
              readonly
              name="picker"
              label="选择审核人"
              placeholder="点击选择审核人"
              :rules="[{ required: true, message: '请选择审核人' }]"
              @click="auditChooseShow = true"
            />
            <van-popup v-model:show="auditChooseShow" destroy-on-close position="bottom">
              <van-picker
                :columns="columns"
                :model-value="auditPickerValue"
                @confirm="onConfirmAudit"
                @cancel="auditChooseShow = false"
              />
            </van-popup>
          </van-cell-group>
          <div style="margin: 16px">
            <van-button round block type="primary" native-type="submit" @click="handleSubmit"
            >下一步
            </van-button>
          </div>
        </van-form>
      </van-tab>
      <van-tab title="申请信息预览" :disabled="!isFinished">
        <AuditResult :visitorInfo="visitorInfo" :auditInfo="auditInfo"></AuditResult>
      </van-tab>
    </van-tabs>
  </div>
</template>
