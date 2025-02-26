import axios from 'axios'
import { getToken, removeToken } from '@/utils/auth'
// @ts-ignore
import Swal from 'sweetalert2/dist/sweetalert2.js'
import { Log } from '@/utils/index'

export interface AxiosResponseConfig<T = any> {
  code: number // 状态码
  msg: string // 返回消息
  token?: string // 认证 token
  userid?: number // 用户 id
  data?: T // 数据对象，可选
  rows?: T[] // 数据对象，可选
}

export function createInstance(url: string, timeout: number) {
  return axios.create({
    baseURL: url, // 设置基础 URL
    timeout: timeout, // 设置请求超时时间
    headers: {
      'Content-Type': 'application/json' // 设置默认请求头
    }
  })
}

const instance = createInstance('http://localhost:5173/api', 5000)

// 请求拦截器
instance.interceptors.request.use(
  (config) => {
    // 例如：添加认证 token
    const token = getToken()

    if (token == `null` || token == 'undefined' || token == '') {
      Log.error(`登录已过期，请重新登录`)
      removeToken()
      return Promise.reject(`登录已过期，请重新登录`)
    }
    config.headers.Authorization = `Bearer ${token}`

    return config
  },
  (error) => {
    // @ts-ignore
    // 对请求错误做些什么
    Swal.fire({
      title: error.message,
      icon: 'error',
      draggable: true
    })
    return Promise.reject(error)
  }
)

// 响应拦截器
instance.interceptors.response.use(
  (response: any) => {
    if (response.code === 200) {
      return response.data
    }

    if (response.code === 401) {
      Log.error(`登录已过期，请重新登录`)
      return Promise.reject(response)
    }

    if (response.code === 403) {
      Log.error(`没有权限访问`)
      window.location.href = '/login'
      return Promise.reject(response)
    }

    // 对响应数据做些什么
    return response.data // 直接返回响应数据
  },
  (error) => {
    // @ts-ignore
    Swal.fire({
      title: error.message,
      icon: 'error',
      draggable: true
    })
    // 重定向到登录页面
    window.location.href = '/login'

    return Promise.reject(error)
  }
)

export default instance
