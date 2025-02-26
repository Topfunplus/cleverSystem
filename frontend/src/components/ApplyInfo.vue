<script setup lang="ts">
import { onMounted, reactive, ref, watch } from 'vue'
import { showConfirmDialog, showDialog } from 'vant'
import type { FormInstance } from 'vant'
import AuditResult from '@/components/AuditResult.vue'
import { AuditStatus } from '@/utils/enum'
import { Log } from '@/utils'

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

// 提交表单
const onSubmit = () => {
  showDialog({
    title: '结果',
    message: '提交申请成功'
  }).then(() => {
    active.value = 1
    isFinished.value = true
    step.value = 1
    // 数据库存值 审核状态为审核中
  })
}

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
})
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
                  v-model="visitorInfo.cardImages"
                  :max-count="2"
                  :rules="[{ required: true, message: '请上传身份证正反面照片' }]"
                />
              </template>
            </van-field>
            <van-field name="faceImage" label="人脸照片">
              <template #input>
                <van-uploader
                  v-model="visitorInfo.faceImage"
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
