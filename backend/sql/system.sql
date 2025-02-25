-- 创建访客信息表
CREATE TABLE visitor_info
(
    id                      INT AUTO_INCREMENT PRIMARY KEY COMMENT '访客ID，主键，唯一标识每个访客',
    NAME                    VARCHAR(100) NOT NULL COMMENT '姓名，访客的真实姓名',
    phone                   VARCHAR(20)  NOT NULL COMMENT '手机号，访客的联系电话',
    id_card                 VARCHAR(20)  NOT NULL COMMENT '身份证号，访客的身份证号码，用于身份识别',
    visitor_unit            VARCHAR(100) COMMENT '访客单位，访客所在单位名称',
    visit_reason            TEXT COMMENT '到访理由，访客到工厂的目的或理由',
    id_card_front_image     varchar(500) COMMENT '身份证正面照片，存储身份证正面的图像（二进制数据）',
    id_card_back_image      varchar(500) COMMENT '身份证反面照片，存储身份证背面的图像（二进制数据）',
    face_image              varchar(500) COMMENT '人脸照片，访客的脸部照片，用于闸机识别（二进制数据）',
    is_car                  BOOLEAN                                   DEFAULT 0 COMMENT '是否开车，0表示未开车，1表示开车，默认未开车',
    car_plate               VARCHAR(20) COMMENT '车牌号，访客的车牌号码，开车时需要填写',
    visit_time              DATE COMMENT '访客时间，访客计划进入的日期（精确到天）',
    visit_duration          INT COMMENT '访问时长，访客预定的访问时间，单位：分钟',
    request_time            TIMESTAMP                                 DEFAULT CURRENT_TIMESTAMP COMMENT '申请时间，访客提交申请的时间（自动生成）',
    STATUS                  ENUM ('审核中', '审核通过', '审核不通过') DEFAULT '审核中' COMMENT '审核状态，表示当前申请的审批状态（待审核、已批准、已拒绝）',
    first_approval_time     TIMESTAMP COMMENT '一级审核时间，第一次审核的时间，表示审核员对申请的时间戳',
    first_approval_opinion  TEXT COMMENT '一级审核意见，审核员对申请的意见或说明',
    second_approval_time    TIMESTAMP COMMENT '二级审核时间，第二次审核的时间，表示二级审核员的审核时间',
    second_approval_opinion TEXT COMMENT '二级审核意见，二级审核员的审核意见或说明'
) COMMENT '用于存储访客相关信息的表';

CREATE TABLE audit_record
(
    id               INT AUTO_INCREMENT PRIMARY KEY COMMENT '审核记录ID，主键，唯一标识每条审核记录',
    visitor_id       INT                     NOT NULL COMMENT '访客ID，外键，关联到访客信息表，标识审核记录属于哪个访客',
    audit_level      ENUM ( '一级', '二级' ) NOT NULL COMMENT '审核级别，表示当前审核的级别，一级或二级',
    auditor_id       INT                     NOT NULL COMMENT '审核人ID，外键，关联到用户表，表示审核人的ID',
    approval_status  ENUM ( '同意', '拒绝' ) NOT NULL COMMENT '审批状态，表示该级审核员对申请的审批结果（同意或拒绝）',
    approval_time    TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '审批时间，表示当前审核的时间（自动生成）',
    approval_opinion TEXT COMMENT '审批意见，审核员对该申请的具体审批意见'
) COMMENT '用于存储访客审核相关记录的表';


CREATE TABLE qr_code_record
(
    id            INT AUTO_INCREMENT PRIMARY KEY COMMENT '二维码ID，主键，唯一标识每条二维码记录',
    visitor_id    INT  NOT NULL COMMENT '访客ID，外键，关联到访客信息表，表示该二维码对应的访客',
    qr_code       TEXT NOT NULL COMMENT '二维码内容，存储二维码的字符串，包含访客的基本信息（如姓名、身份证号等）',
    creation_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间，二维码生成的时间（自动生成）'
) COMMENT '用于存储访客二维码相关记录的表';

CREATE TABLE visit_record
(
    id         INT AUTO_INCREMENT PRIMARY KEY COMMENT '访问记录ID，主键，唯一标识每条进出记录',
    visitor_id INT NOT NULL COMMENT '访客ID，外键，关联到访客信息表，标识该访问记录属于哪个访客',
    entry_time TIMESTAMP COMMENT '进入时间，访客进入工厂的具体时间（时间戳）',
    exit_time  TIMESTAMP COMMENT '离开时间，访客离开工厂的具体时间（时间戳）',
    is_reentry BOOLEAN DEFAULT 0 COMMENT '是否是重新进入，0表示首次进入，1表示重新进入，默认首次进入'
) COMMENT '用于存储访客访问工厂进出记录的表';