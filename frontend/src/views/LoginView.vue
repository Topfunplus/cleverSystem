<script setup lang="ts">
import { ref, onMounted, nextTick } from 'vue'
import { getCaptchaImage } from '@/api/login'
import { useUserStore } from '@/stores/user'
import type { AxiosResponseConfig } from '@/utils/http'
import { useRouter } from 'vue-router'
// @ts-ignore
import Swal from 'sweetalert2/dist/sweetalert2.js'
import { Log } from '@/utils'

const active = ref(0)
// 登录信息
const phonenumber = ref('')
const idCard = ref('')
const password = ref('')
// 注册信息
const registerPhonenumber = ref('')
const registerPassword = ref('')
const registeridCard = ref('')
const confirmPassword = ref('')
const captcha = ref('')
const registerCaptcha = ref('')
const captchaImg = ref('')
const registerCaptchaImg = ref('')
const uuid = ref('')
const registerUuid = ref('')

// pinia用户信息
const userStore = useUserStore()
// router
const router = useRouter()
// 获取验证码
const getCaptcha = async (isRegister = false) => {
  getCaptchaImage().then((res) => {
    if (res.code === 200) {
      if (isRegister) {
        registerCaptchaImg.value = 'data:image/gif;base64,' + res.img
        registerUuid.value = res.uuid
      } else {
        captchaImg.value = 'data:image/gif;base64,' + res.img
        uuid.value = res.uuid
      }
    } else {
      Swal.fire({
        icon: 'error',
        title: res.msg
      })
    }
  })
}

// 点击验证码图片刷新
const refreshCaptcha = (isRegister = false) => {
  getCaptcha(isRegister)
}

// 在组件挂载时获取验证码
onMounted(() => {
  getCaptcha(false)
  getCaptcha(true)
})

// 切换标签页时刷新对应的验证码
const handleTabChange = (name: number) => {
  if (name === 1) {
    getCaptcha(true)
  } else {
    getCaptcha(false)
  }
}

// 登录接口
async function login() {
  if (!phonenumber.value || !password.value) {
    Swal.fire({
      title: '请填写完整信息',
      icon: 'error',
      draggable: true
    })
    return
  }
  if (!captcha.value) {
    Swal.fire({
      title: '请输入验证码',
      icon: 'error',
      draggable: true
    })
    return
  }

  const loginBody: LoginUserInfo = {
    phonenumber: phonenumber.value,
    password: password.value,
    code: captcha.value,
    uuid: uuid.value,
    idCard: idCard.value
  }
  const res = await userStore.loginOn(loginBody)

  if (res.code === 200) router.push('/')
}

// 注册接口
function register() {
  if (!registerPhonenumber.value || !registerPassword.value || !confirmPassword.value) {
    Swal.fire({
      title: '请填写完整信息',
      icon: 'error',
      draggable: true
    })
    return
  }
  if (!registerCaptcha.value) {
    Swal.fire({
      title: '请输入验证码',
      icon: 'error',
      draggable: true
    })
    return
  }
  if (registerPassword.value !== confirmPassword.value) {
    Swal.fire({
      title: '两次输入的密码不一致',
      icon: 'error',
      draggable: true
    })
    return
  }

  // 进行注册
  userStore
    .registerOn({
      phonenumber: registerPhonenumber.value,
      password: registerPassword.value,
      code: registerCaptcha.value,
      uuid: registerUuid.value,
      idCard: registeridCard.value
    })
    .then((res: AxiosResponseConfig) => {
      if (res.code === 200) {
        Log.success(`注册成功`)
        // reset()
        // nextTick(() => {
        //   active.value = 0
        // })
        location.reload()
      } else {
        Log.error(res.msg)
      }
    })
    .catch((err: AxiosResponseConfig) => {
      Log.error(err.msg)
    })
}

// 切换注册和登录
const switchToRegister = () => {
  active.value = 1
}

const switchToLogin = () => {
  active.value = 0
}

function reset() {
  // 重置登录信息和注册信息
  phonenumber.value = ''
  registerPhonenumber.value = ''
  registerPassword.value = ''
  registeridCard.value = ''
  confirmPassword.value = ''
  password.value = ''
  captcha.value = ''
  registerCaptcha.value = ''
  captchaImg.value = ''
  registerCaptchaImg.value = ''
  uuid.value = ''
  registerUuid.value = ''
}

onMounted(() => {
  Log.info('您尚未登录，请先登录!')
})
</script>

<template>
  <div class="login-container">
    <h1 class="login-title">智慧安防系统登录</h1>
    <div class="login-box">
      <van-tabs v-model:active="active" animated @change="handleTabChange">
        <van-tab title="登录" :name="0">
          <div class="form-container">
            <van-cell-group inset>
              <van-field
                v-model="phonenumber"
                label="手机号"
                placeholder="请输入手机号"
                :rules="[{ required: true, message: '请填写手机号' }]"
              />
              <van-field
                v-model="idCard"
                label="身份证号"
                placeholder="请输入身份证号"
                :rules="[{ required: true, message: '请填写银行卡号' }]"
              />
              <van-field
                v-model="password"
                label="密码"
                type="password"
                placeholder="请输入密码"
                :rules="[{ required: true, message: '请填写密码' }]"
              />
              <van-field
                v-model="captcha"
                label="验证码"
                placeholder="请输入验证码"
                :rules="[{ required: true, message: '请填写验证码' }]"
              >
                <template #right-icon>
                  <img
                    v-if="captchaImg"
                    :src="captchaImg"
                    class="captcha-img"
                    @click="refreshCaptcha(false)"
                    alt="验证码"
                  />
                </template>
              </van-field>
            </van-cell-group>
            <div class="button-container">
              <van-button round block type="primary" @click="login">登录</van-button>
              <div class="switch-text" @click="switchToRegister">未注册？去注册</div>
            </div>
          </div>
        </van-tab>

        <van-tab title="注册" :name="1">
          <div class="form-container">
            <van-cell-group inset>
              <van-field
                v-model="registerPhonenumber"
                label="手机号"
                placeholder="请输入手机号"
                :rules="[{ required: true, message: '请填写手机号' }]"
              />
              <van-field
                v-model="registeridCard"
                label="身份证号"
                placeholder="请输入身份证号"
                :rules="[{ required: true, message: '请填写身份证号' }]"
              />
              <van-field
                v-model="registerPassword"
                label="密码"
                type="password"
                placeholder="请输入密码"
                :rules="[{ required: true, message: '请填写密码' }]"
              />
              <van-field
                v-model="confirmPassword"
                label="确认密码"
                type="password"
                placeholder="请再次输入密码"
                :rules="[{ required: true, message: '请确认密码' }]"
              />
              <van-field
                v-model="registerCaptcha"
                label="验证码"
                placeholder="请输入验证码"
                :rules="[{ required: true, message: '请填写验证码' }]"
              >
                <template #right-icon>
                  <img
                    v-if="registerCaptchaImg"
                    :src="registerCaptchaImg"
                    class="captcha-img"
                    @click="refreshCaptcha(true)"
                    alt="验证码"
                  />
                </template>
              </van-field>
            </van-cell-group>
            <div class="button-container">
              <van-button round block type="primary" @click="register">注册</van-button>
              <div class="switch-text" @click="switchToLogin">已有账号？去登录</div>
            </div>
          </div>
        </van-tab>
      </van-tabs>
    </div>
  </div>
</template>

<style scoped>
.login-container {
  padding: 20px;
  min-height: 100vh;
  background-color: #f7f8fa;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 100px;
  box-sizing: border-box;
}

.login-title {
  font-size: 24px;
  font-weight: bold;
  color: #323233;
  margin-bottom: 30px;
  text-align: center;
}

.login-box {
  width: 100%;
  max-width: 375px;
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  padding: 20px 0;
}

.form-container {
  margin-top: 20px;
}

.button-container {
  margin-top: 24px;
  padding: 0 16px;
}

.switch-text {
  text-align: center;
  margin-top: 12px;
  color: #1989fa;
  font-size: 14px;
}

:deep(.van-tabs__line) {
  background-color: #1989fa;
}

:deep(.van-cell-group--inset) {
  margin: 0;
}

:deep(.van-field__label) {
  width: 60px;
}

.captcha-img {
  height: 36px;
  width: 120rpx;
  cursor: pointer;
  vertical-align: middle;
}

:deep(.van-field__right-icon) {
  height: 32px;
  padding: 0;
}
</style>
