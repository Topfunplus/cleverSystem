interface LoginUserInfo {
  code: string
  uuid: string
  password: string
  phonenumber: string
  idCard: string
}

interface RegisterUserInfo extends LoginUserInfo {}
