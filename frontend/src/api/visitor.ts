import instance, { type AxiosResponseConfig } from '@/utils/http'

// 后端对象定义
export interface VisitorInfoDomain {
  id?: number | null;
  name: string | null;
  phone: string | null;
  idCard: string | null;
  visitorUnit: string | null;
  visitReason: string | null;
  idCardFrontImage: string | null;
  idCardBackImage: string | null;
  faceImage: string | null;
  isCar?: number | null;
  carPlate?: string | null;
  visitTime: string | null;
  visitDuration: number | null;
  requestTime: string | null;
  status: string | null;
  // 审核意见相关
  firstApprovalTime?: string | null;
  firstApprovalOpinion?: string | null;
  secondApprovalTime?: string | null;
  secondApprovalOpinion?: string | null;
}

export function listVisitorInfo(query: VisitorInfoDomain): Promise<AxiosResponseConfig> {
  return instance({
    url: '/system/visitor_info/list',
    method: 'get',
    params: query
  })
}

// 查询用于存储访客相关信息的详细
export function getVisitorInfo(id: number): Promise<AxiosResponseConfig> {
  return instance({
    url: '/system/visitor_info/' + id,
    method: 'get'
  })
}

// 新增用于存储访客相关信息的
export function addVisitorInfo(data: VisitorInfoDomain): Promise<AxiosResponseConfig> {
  return instance({
    url: '/system/visitor_info',
    method: 'post',
    data: data
  })
}

// 修改用于存储访客相关信息的
export function updateVisitorInfo(data: VisitorInfoDomain): Promise<AxiosResponseConfig> {
  return instance({
    url: '/system/visitor_info',
    method: 'put',
    data: data
  })
}

// 删除用于存储访客相关信息的
export function delVisitorInfo(id: number): Promise<AxiosResponseConfig> {
  return instance({
    url: '/system/visitor_info/' + id,
    method: 'delete'
  })
}
