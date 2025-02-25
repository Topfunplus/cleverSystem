<script setup lang="ts">
import { ref, onMounted } from 'vue';

const active = ref('login');
const username = ref('');
const password = ref('');
const registerUsername = ref('');
const registerPassword = ref('');
const confirmPassword = ref('');
const captcha = ref('');
const registerCaptcha = ref('');
const captchaImg = ref('');
const registerCaptchaImg = ref('');
const uuid = ref('');
const registerUuid = ref('');

// 获取验证码
const getCaptcha = async (isRegister = false) => {
  try {
    const response = await fetch('/captchaImage');
    const data = await response.json();
    if (data.code === 200) {
      if (isRegister) {
        registerCaptchaImg.value = 'data:image/jpg;base64,' + data.img;
        registerUuid.value = data.uuid;
      } else {
        captchaImg.value = 'data:image/jpg;base64,' + data.img;
        uuid.value = data.uuid;
      }
    } else {
      showToast('获取验证码失败');
    }
  } catch (error) {
    showToast('获取验证码失败');
  }
};

// 点击验证码图片刷新
const refreshCaptcha = (isRegister = false) => {
  getCaptcha(isRegister);
};

// 在组件挂载时获取验证码
onMounted(() => {
  getCaptcha(false);
  getCaptcha(true);
});

// 切换标签页时刷新对应的验证码
const handleTabChange = (name: string) => {
  if (name === 'register') {
    getCaptcha(true);
  } else {
    getCaptcha(false);
  }
};

const login = () => {
  if (!username.value || !password.value) {
    showToast('请填写完整信息');
    return;
  }
  if (!captcha.value) {
    showToast('请输入验证码');
    return;
  }
  console.log('username:', username.value);
  console.log('password:', password.value);
  console.log('captcha:', captcha.value);
  console.log('uuid:', uuid.value);
};

const register = () => {
  if (!registerUsername.value || !registerPassword.value || !confirmPassword.value) {
    showToast('请填写完整信息');
    return;
  }
  if (!registerCaptcha.value) {
    showToast('请输入验证码');
    return;
  }
  if (registerPassword.value !== confirmPassword.value) {
    showToast('两次输入的密码不一致');
    return;
  }
  console.log('注册信息:', {
    username: registerUsername.value,
    password: registerPassword.value,
    captcha: registerCaptcha.value,
    uuid: registerUuid.value
  });
};

const switchToRegister = () => {
  active.value = 'register';
};

const switchToLogin = () => {
  active.value = 'login';
};
</script>

<template>
  <div class="login-container">
    <h1 class="login-title">智慧安防系统登录</h1>
    <div class="login-box">
      <van-tabs v-model:active="active" animated @change="handleTabChange">
        <van-tab title="登录" name="login">
          <div class="form-container">
            <van-cell-group inset>
              <van-field v-model="username" label="用户名" placeholder="请输入用户名"
                :rules="[{ required: true, message: '请填写用户名' }]" />
              <van-field v-model="password" label="密码" type="password" placeholder="请输入密码"
                :rules="[{ required: true, message: '请填写密码' }]" />
              <van-field v-model="captcha" label="验证码" placeholder="请输入验证码"
                :rules="[{ required: true, message: '请填写验证码' }]">
                <template #right-icon>
                  <img v-if="captchaImg" :src="captchaImg" class="captcha-img" @click="refreshCaptcha" alt="验证码" />
                </template>
              </van-field>
            </van-cell-group>
            <div class="button-container">
              <van-button round block type="primary" @click="login">登录</van-button>
              <div class="switch-text" @click="switchToRegister">
                未注册？去注册
              </div>
            </div>
          </div>
        </van-tab>

        <van-tab title="注册" name="register">
          <div class="form-container">
            <van-cell-group inset>
              <van-field v-model="registerUsername" label="用户名" placeholder="请输入用户名"
                :rules="[{ required: true, message: '请填写用户名' }]" />
              <van-field v-model="registerPassword" label="密码" type="password" placeholder="请输入密码"
                :rules="[{ required: true, message: '请填写密码' }]" />
              <van-field v-model="confirmPassword" label="确认密码" type="password" placeholder="请再次输入密码"
                :rules="[{ required: true, message: '请确认密码' }]" />
              <van-field v-model="registerCaptcha" label="验证码" placeholder="请输入验证码"
                :rules="[{ required: true, message: '请填写验证码' }]">
                <template #right-icon>
                  <img v-if="registerCaptchaImg" :src="registerCaptchaImg" class="captcha-img"
                    @click="refreshCaptcha(true)" alt="验证码" />
                </template>
              </van-field>
            </van-cell-group>
            <div class="button-container">
              <van-button round block type="primary" @click="register">注册</van-button>
              <div class="switch-text" @click="switchToLogin">
                已有账号？去登录
              </div>
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
  height: 32px;
  cursor: pointer;
  vertical-align: middle;
}

:deep(.van-field__right-icon) {
  height: 32px;
  padding: 0;
}
</style>