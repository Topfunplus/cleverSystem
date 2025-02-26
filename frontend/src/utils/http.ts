import axios from 'axios'

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
    console.log('Request Interceptor - Sending Request:', config)
    // 例如：添加认证 token
    const token = localStorage.getItem('token') // 假设 token 存储在 localStorage 中
    if (token) {
      config.headers.Authorization = `Bearer ${token}`
    }

    return config
  },
  (error) => {
    // 对请求错误做些什么
    console.error('Request Interceptor - Error:', error)
    return Promise.reject(error)
  }
)

// 响应拦截器
instance.interceptors.response.use(
  (response) => {
    // 对响应数据做些什么
    console.log('Response Interceptor - Received Response:', response.data)
    return response.data // 直接返回响应数据
  },
  (error) => {
    // 对响应错误做些什么
    console.error(
      'Response Interceptor - Error:',
      error.response ? error.response.data : error.message
    )

    // 例如：处理 401 未授权错误
    if (error.response && error.response.status === 401) {
      console.log('Unauthorized! Redirecting to login...')
      // 重定向到登录页面
      window.location.href = '/login'
    }

    return Promise.reject(error)
  }
)

export default instance
