interface LoginUserInfo {
  phone: string
  idCard?: string
  password: string
}

interface RegisterUserInfo extends LoginUserInfo {
  phone: string
  idCard: string
  password: string
}
