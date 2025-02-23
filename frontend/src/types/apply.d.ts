// 访客信息
interface VisitorInfo {
  visitorName: string
  phone: string
  idCard: string
  visitorUnit: string
  visitReason: string
  cardImages: []
  faceImage: []
  visitTime: string
  visitStartTime: string
  visitEndTime: string
}

// 申请列信息
interface AuditColumn {
  text: string // 值
  value: number // id
}

// 审核人信息 传入id和name
interface AuditInfo {
  id: number
  name: string
}
