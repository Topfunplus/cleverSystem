import instance, { type AxiosResponseConfig } from '@/utils/http'

export function getCaptchaImage(): Promise<{
  msg: string
  img: string
  code: number
  captchaEnabled: boolean
  uuid: string
}> {
  return instance.request({
    url: '/captchaImage',
    method: 'GET'
  })
}

export function login(userInfo: LoginUserInfo): Promise<AxiosResponseConfig> {
  return instance.request({
    url: '/login',
    method: 'POST',
    data: userInfo
  })
}

export function register(registerInfo: RegisterUserInfo): Promise<AxiosResponseConfig> {
  return instance.request({
    url: '/register',
    method: 'POST',
    data: registerInfo
  })
}
