<script setup lang="ts">
import { reactive, ref } from 'vue'

const active = ref(0)

// 切换tab页
function onClickTab({ title }: { title: string }) {
  console.log(title)
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
  console.log(visitorInfo)
}

// 选择时间确认
const dateType = ref(true)
// 时间展示用
const dateValue = ref('')
const showPicker = ref(false)
const pickerValue = ref<string[]>([])

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

function handleSubmit() {
  auditShow.value = true
}

// 审核人选择
const auditInfo = reactive<AuditInfo>({
  id: null,
  name: null
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
</script>

<template>
  <div>
    <van-tabs v-model:active="active" @click-tab="onClickTab">
      <van-tab title="填写申请信息">
        <van-form @submit="onSubmit">
          <van-cell-group inset>
            <van-field
              v-model="visitorInfo.visitorName"
              name="姓名"
              label="姓名"
              placeholder="姓名"
              :rules="[
                { required: true, message: '请填写姓名' },
                { min: 2, message: '姓名至少两个字符' }
              ]"
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
              :rules="[
                { required: true, message: '请填写访客单位' },
                { min: 2, message: '访客单位至少两个字符' }
              ]"
            />
            <van-field
              v-model="visitorInfo.visitReason"
              name="到访理由"
              label="到访理由"
              placeholder="访客单位"
              :rules="[
                { required: true, message: '请填写到访理由' },
                { min: 5, message: '到访理由至少五个字符' }
              ]"
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
              >提交
            </van-button>
          </div>
        </van-form>
      </van-tab>
      <van-tab title="申请信息预览">内容 2</van-tab>
    </van-tabs>
  </div>
</template>
