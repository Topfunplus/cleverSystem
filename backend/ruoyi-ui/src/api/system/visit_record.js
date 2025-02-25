import request from '@/utils/request'

// 查询用于存储访客审核相关记录的列表
export function listVisitRecord(query) {
  return request({
    url: '/system/visit_record/list',
    method: 'get',
    params: query
  })
}

// 查询用于存储访客审核相关记录的详细
export function getVisitRecord(id) {
  return request({
    url: '/system/visit_record/' + id,
    method: 'get'
  })
}

// 新增用于存储访客审核相关记录的
export function addVisitRecord(data) {
  return request({
    url: '/system/visit_record',
    method: 'post',
    data: data
  })
}

// 修改用于存储访客审核相关记录的
export function updateVisitRecord(data) {
  return request({
    url: '/system/visit_record',
    method: 'put',
    data: data
  })
}

// 删除用于存储访客审核相关记录的
export function delVisitRecord(id) {
  return request({
    url: '/system/visit_record/' + id,
    method: 'delete'
  })
}
