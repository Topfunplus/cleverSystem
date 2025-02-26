import { defineStore } from 'pinia'
import { getToken, setToken } from '@/utils/auth'
import { login, register } from '@/api/login'
import type { AxiosResponseConfig } from '@/utils/http'

import { Log } from '@/utils'
import { getProfile, type SysUser } from '@/api/user'

interface UserState {
  token: string
  name: string
  avator: string
  roles: []
  userInfo: SysUser
  permissions: string[]
}

export const useUserStore = defineStore('user', {
  state: (): UserState => ({
    token: getToken(),
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
            if (res.code === 200) {
              // 设置token到pinia
              this.setToken(res.token as string)
              // 设置id
              this.setUserInfo({ id: res.userid })
              setToken(res.token as string)
              Log.success(res.msg)
            }
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
    // 获取当前用户信息的方法
    getInfo(): Promise<AxiosResponseConfig> {
      return new Promise((resolve: any, reject: any) => {
        if (this.token) {
          getProfile()
            .then((res: AxiosResponseConfig) => {
              if (res.code === 200) {
                console.log(res)
                resolve(res)
              } else {
                reject(res)
              }
            })
            .catch((err) => {
              reject(err)
            })
        }
      })
    },
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
