-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用于存储访客二维码相关记录的', '3', '1', 'qr_code_record', 'system/qr_code_record/index', 1, 0, 'C', '0', '0', 'system:qr_code_record:list', '#', 'admin', sysdate(), '', null, '用于存储访客二维码相关记录的菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用于存储访客二维码相关记录的查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:qr_code_record:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用于存储访客二维码相关记录的新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:qr_code_record:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用于存储访客二维码相关记录的修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:qr_code_record:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用于存储访客二维码相关记录的删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:qr_code_record:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用于存储访客二维码相关记录的导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:qr_code_record:export',       '#', 'admin', sysdate(), '', null, '');