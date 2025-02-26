import instance, { type AxiosResponseConfig } from '@/utils/http'

export interface BaseEntity {
  createBy?: string
  createTime?: Date
  updateBy?: string
  updateTime?: Date
  remark?: string
}

export interface SysUser extends BaseEntity {
  // 姓名
  name?: string
  /** 用户ID */
  userId?: number
  /** 部门ID */
  deptId?: number
  /** 用户账号 */
  userName?: string
  /** 用户昵称 */
  nickName?: string
  /** 用户邮箱 */
  email?: string
  /** 手机号码 */
  phonenumber?: string
  /** 用户性别 */
  sex?: string
  /** 用户头像 */
  avatar?: string
  /** 密码 */
  password?: string
  /** 帐号状态（0正常 1停用） */
  status?: string
  /** 删除标志（0代表存在 2代表删除） */
  delFlag?: string
  /** 最后登录IP */
  loginIp?: string
  /** 最后登录时间 */
  loginDate?: Date
  /** 角色组 */
  roleIds?: number[]
  /** 岗位组 */
  postIds?: number[]
  /** 角色ID */
  roleId?: number
}

// 查询个人信息
export function getProfile(): Promise<AxiosResponseConfig> {
  return instance({
    url: '/system/user/profile',
    method: 'get'
  })
}

// 查询用户列表
export function listUser(query: SysUser): Promise<AxiosResponseConfig> {
  return instance({
    url: '/system/user/list',
    method: 'get',
    params: query
  })
}

// 查询用户详细
export function getUser(userId: number): Promise<AxiosResponseConfig> {
  return instance({
    url: '/system/user/' + userId,
    method: 'get'
  })
}

// 新增用户
export function addUser(data: SysUser): Promise<AxiosResponseConfig> {
  return instance({
    url: '/system/user',
    method: 'post',
    data: data
  })
}

// 修改用户
export function updateUser(data: SysUser): Promise<AxiosResponseConfig> {
  return instance({
    url: '/system/user',
    method: 'put',
    data: data
  })
}

// 删除用户
export function delUser(userId: number): Promise<AxiosResponseConfig> {
  return instance({
    url: '/system/user/' + userId,
    method: 'delete'
  })
}

// 用户密码重置
export function resetUserPwd(userId: number, password: string): Promise<AxiosResponseConfig> {
  const data = {
    userId,
    password
  }
  return instance({
    url: '/system/user/resetPwd',
    method: 'put',
    data: data
  })
}

// 用户状态修改
export function changeUserStatus(userId: number, status: string): Promise<AxiosResponseConfig> {
  const data = {
    userId,
    status
  }
  return instance({
    url: '/system/user/changeStatus',
    method: 'put',
    data: data
  })
}

// 查询用户个人信息
export function getUserProfile(): Promise<AxiosResponseConfig> {
  return instance({
    url: '/system/user/profile',
    method: 'get'
  })
}

// 修改用户个人信息
export function updateUserProfile(data: SysUser): Promise<AxiosResponseConfig> {
  return instance({
    url: '/system/user/profile',
    method: 'put',
    data: data
  })
}

// 用户密码重置
export function updateUserPwd(
  oldPassword: string,
  newPassword: string
): Promise<AxiosResponseConfig> {
  const data = {
    oldPassword,
    newPassword
  }
  return instance({
    url: '/system/user/profile/updatePwd',
    method: 'put',
    data: data
  })
}

// 用户头像上传
export function uploadAvatar(data: any): Promise<AxiosResponseConfig> {
  return instance({
    url: '/system/user/profile/avatar',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data: data
  })
}

// 查询授权角色
export function getAuthRole(userId: number): Promise<AxiosResponseConfig> {
  return instance({
    url: '/system/user/authRole/' + userId,
    method: 'get'
  })
}

// 保存授权角色
export function updateAuthRole(data: any): Promise<AxiosResponseConfig> {
  return instance({
    url: '/system/user/authRole',
    method: 'put',
    params: data
  })
}

// 查询部门下拉树结构
export function deptTreeSelect(): Promise<AxiosResponseConfig> {
  return instance({
    url: '/system/user/deptTree',
    method: 'get'
  })
}
