import request from '@/utils/request'

// 查询用于存储访客审核相关记录的列表
export function listVisitorRecord(query) {
  return request({
    url: '/system/visitor_record/list',
    method: 'get',
    params: query
  })
}

// 查询用于存储访客审核相关记录的详细
export function getVisitorRecord(id) {
  return request({
    url: '/system/visitor_record/' + id,
    method: 'get'
  })
}

// 新增用于存储访客审核相关记录的
export function addVisitorRecord(data) {
  return request({
    url: '/system/visitor_record',
    method: 'post',
    data: data
  })
}

// 修改用于存储访客审核相关记录的
export function updateVisitorRecord(data) {
  return request({
    url: '/system/visitor_record',
    method: 'put',
    data: data
  })
}

// 删除用于存储访客审核相关记录的
export function delVisitorRecord(id) {
  return request({
    url: '/system/visitor_record/' + id,
    method: 'delete'
  })
}
