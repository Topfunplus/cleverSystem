<script setup lang="ts">
import { ref } from 'vue'
import { showDialog } from 'vant'
import { handleDownloadUtil } from './untils'

interface VisitorInfo {
  visitorName: string
  phone: string
  idCard: string
  visitorUnit: string
  visitReason: string
}

const props = defineProps({
  // 访客信息
  visitorInfo: {
    type: Object as () => VisitorInfo,
    default: () => ({
      visitorName: '张三',
      phone: '13800138000',
      idCard: '110101199003077632',
      visitorUnit: '某某科技有限公司',
      visitReason: '业务洽谈'
    })
  },
  // 审核信息
  auditInfo: {
    type: Object as () => AuditInfo,
    default: () => ({
      name: '李主任'
    })
  },
  // 访问时间显示值
  dateValue: {
    type: String,
    default: '2023-09-01 09:00 至 2023-09-01 18:00'
  },
  // 二维码图片地址
  qrcodeUrl: {
    type: String,
    default: 'https://fastly.jsdelivr.net/npm/@vant/assets/qrcode.png'
  },
  // 访问有效期
  visitDuration: {
    type: String,
    default: '2023-09-01 至 2023-09-05'
  }
})
// 下载处理
const handleDownload = () => {
  console.log('触发下载逻辑')
  handleDownloadUtil(props.qrcodeUrl, '二维码凭证')
  // 这里可以添加实际的下载逻辑
  // 例如：生成包含二维码的图片并下载
}

const handleReaudit = () => {
  showDialog({
    title: '提示',
    message: '是否重新审核',
    showConfirmButton: true,
    showCancelButton: true,
    width: '320px'
  })
    .then(() => {
      console.log('用户确认重新审核')
      // 这里可以添加实际的重新审核逻辑
    })
    .catch(() => {
      console.log('用户取消重新审核')
    })
}
</script>

<template>
  <div class="audit-result-container">
    <van-steps :active="2" active-icon="success" active-color="#1989fa">
      <van-step>填写申请信息</van-step>
      <van-step>申请进度</van-step>
      <van-step>查看审核结果</van-step>
    </van-steps>

    <div class="audit-pass-container">
      <!-- 基本信息展示 -->
      <van-cell-group title="访客信息" class="info-section">
        <van-cell title="姓名" :value="visitorInfo.visitorName" />
        <van-cell title="手机号" :value="visitorInfo.phone" />
        <van-cell title="身份证号" :value="visitorInfo.idCard" />
        <van-cell title="访客单位" :value="visitorInfo.visitorUnit" />
        <van-cell title="到访理由" :value="visitorInfo.visitReason" />
        <van-cell title="访问时间" :value="dateValue" />
        <van-cell title="审核状态">
          <van-tag type="success">已通过</van-tag>
        </van-cell>
        <van-cell title="审核人" :value="auditInfo.name" />
      </van-cell-group>

      <!-- 二维码展示 -->
      <div class="qrcode-section">
        <div class="qrcode-title">访客通行二维码</div>
        <van-image width="60vw" height="60vw" :src="qrcodeUrl" fit="contain" class="qrcode-img" />
        <div class="tips">
          <van-icon name="warning" color="#ff976a" />
          该二维码有效期为：{{ visitDuration }}
        </div>
        <div class="tips-text">请出示此二维码进行闸机核验</div>
      </div>

      <!-- 操作按钮 -->
      <div class="action-buttons">
        <van-button round block type="primary" icon="down" @click="handleDownload">
          下载电子凭证
        </van-button>
        <van-button round block @click="handleReaudit" class="reaudit-btn"> 重新审核 </van-button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.audit-result-container {
  width: 100vw;
  overflow: hidden;
  box-sizing: content-box;
}
/* 保持原有样式不变 */
.audit-pass-container {
  padding: 16px;
  background: #f7f8fa;
  min-height: calc(100vh - 100px);
}

.info-section {
  margin-bottom: 20px;
  border-radius: 8px;
  overflow: hidden;
}

.qrcode-section {
  background: white;
  padding: 20px;
  border-radius: 8px;
  text-align: center;
}

.qrcode-title {
  font-size: 18px;
  color: #333;
  margin-bottom: 15px;
  font-weight: bold;
}

.tips {
  margin-top: 15px;
  color: #666;
  font-size: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.tips-text {
  color: #999;
  font-size: 12px;
  margin-top: 8px;
}

.action-buttons {
  margin: 30px 16px 0;
}

.qrcode-img {
  margin: 0 auto;
  border: 1px solid #eee;
  padding: 10px;
  border-radius: 8px;
}
.reaudit-btn {
  margin-top: 10px;
}
</style>
