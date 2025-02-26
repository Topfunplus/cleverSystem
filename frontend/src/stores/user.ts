import { defineStore } from 'pinia'
import { getToken, setToken } from '@/utils/auth'
import { login, register } from '@/api/login'
import type { AxiosResponseConfig } from '@/utils/http'
// @ts-ignore
import Swal from 'sweetalert2/dist/sweetalert2.js'

interface UserState {
  token: string
  id: string
  name: string
  avator: string
  roles: []
  userInfo: any
  permissions: string[]
}

export const useUserStore = defineStore('user', {
  state: (): UserState => ({
    token: getToken(),
    id: '',
    name: '',
    avator: '',
    roles: [],
    userInfo: {},
    permissions: []
  }),
  getters: {
    findPermissions: (state) => {
      return (permission: string) => {
        return state.permissions.includes(permission)
      }
    },
    getPermissions: (state) => {
      return state.permissions
    }
  },
  actions: {
    // 用户登录方法
    loginOn(loginBody: LoginUserInfo) {
      return new Promise<AxiosResponseConfig>((resolve, reject) => {
        login(loginBody)
          .then((res: AxiosResponseConfig) => {
            this.setToken(res.token as string)
            setToken(res.token as string)
            Swal.fire({
              title: '登录成功!',
              icon: 'success',
              draggable: true
            })
            resolve(res)
          })
          .catch((err: any) => {
            reject(err)
          })
      })
    },
    // 注册方法
    registerOn(registerBody: RegisterUserInfo) {
      return new Promise<AxiosResponseConfig>((resolve: any, reject: any) => {
        register(registerBody)
          .then((res: AxiosResponseConfig) => {
            resolve(res)
          })
          .catch((err: AxiosResponseConfig) => {
            reject(err)
          })
      })
    },
    // 获取用户信息的方法
    getInfo() {},
    // 登出方法
    logout() {
      return new Promise((resolve: any): void => {
        this.resetToken()
        this.resetUserInfo()
        this.resetPermissions()
        resolve()
      })
    },
    // 设置用户信息
    setUserInfo(userInfo: any) {
      this.userInfo = userInfo
    },
    // 设置token
    setToken(token: string) {
      this.token = token
    },
    // 设置权限列表
    setPermissions(permissions: string[]) {
      this.permissions = permissions
    },
    // 清除用户信息
    resetUserInfo() {
      this.userInfo = {}
    },
    // 清除token
    resetToken() {
      this.token = ''
    },
    // 清除权限列表
    resetPermissions() {
      this.permissions = []
    }
  }
})
