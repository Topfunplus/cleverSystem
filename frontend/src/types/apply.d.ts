// 访客信息
interface VisitorInfo {
  visitorName: string
  phone: string
  idCard: string
  visitorUnit: string
  visitReason: string
  cardImages: string[]
  faceImage: string[]
  visitTime: string
  visitStartTime: string
  visitEndTime: string
}

interface PropsVisitorInfo {
  visitorName: ''
  phone: ''
  idCard: ''
  visitorUnit: ''
  visitReason: ''
  visitTime: ''
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
  status: string
}
