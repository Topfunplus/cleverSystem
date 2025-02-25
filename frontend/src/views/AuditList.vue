<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { showToast, showDialog } from 'vant';

// 访客申请列表数据
const visitList = ref([
    {
        id: 1,
        name: '张三',
        phone: '13800138000',
        visitTime: '2025-02-25 15:00',
        leaveTime: '2025-02-25 17:00',
        reason: '业务洽谈',
        status: 'pending',
        createTime: '2025-02-24 14:30',
        idCard: '310000199001011234',
        visitLocation: 'A栋3层会议室',
    },
    {
        id: 2,
        name: '李四',
        phone: '13900139000',
        visitTime: '2025-02-26 10:00',
        leaveTime: '2025-02-26 11:30',
        reason: '设备维护',
        status: 'pending',
        createTime: '2025-02-24 16:45',
        idCard: '310000199002022345',
        visitLocation: 'B栋机房',
    },
]);

// 处理审核通过
const handleApprove = async (item: any) => {
    try {
        await showDialog({
            title: '确认通过',
            message: `确定通过访客 ${item.name} 的申请吗？`,
            showCancelButton: true,
        });
        // TODO: 调用审核通过接口
        showToast('审核通过成功');
    } catch {
        // 用户取消操作
    }
};

// 处理审核拒绝
const handleReject = async (item: any) => {
    try {
        await showDialog({
            title: '确认拒绝',
            message: `确定拒绝访客 ${item.name} 的申请吗？`,
            showCancelButton: true,
        });
        // TODO: 调用审核拒绝接口
        showToast('已拒绝该申请');
    } catch {
        // 用户取消操作
    }
};

// 刷新列表
const refreshList = () => {
    // TODO: 调用获取列表接口
};

onMounted(() => {
    refreshList();
});
</script>

<template>
    <div class="audit-container">
        <van-nav-bar title="访客审核列表" fixed placeholder>
            <template #right>
                <van-icon name="replay" @click="refreshList" />
            </template>
        </van-nav-bar>

        <div class="list-container">
            <van-pull-refresh @refresh="refreshList">
                <van-list>
                    <div v-for="item in visitList" :key="item.id" class="visit-card">
                        <van-cell-group inset>
                            <van-cell>
                                <template #title>
                                    <div class="cell-content">
                                        <div class="info-content">
                                            <div class="visitor-header">
                                                <div class="visitor-info">
                                                    <span class="visitor-name">{{ item.name }}</span>
                                                    <van-tag type="warning" class="status-tag">待审核</van-tag>
                                                </div>
                                                <div class="action-buttons">
                                                    <van-button type="success" size="mini"
                                                        @click="handleApprove(item)">通过</van-button>
                                                    <van-button type="danger" size="mini"
                                                        @click="handleReject(item)">拒绝</van-button>
                                                </div>
                                            </div>
                                            <div class="info-row">
                                                <van-icon name="phone-o" />
                                                <span>{{ item.phone }}</span>
                                            </div>
                                            <div class="info-row">
                                                <van-icon name="clock-o" />
                                                <span>{{ item.visitTime }} - {{ item.leaveTime }}</span>
                                            </div>
                                            <div class="info-row">
                                                <van-icon name="location-o" />
                                                <span>{{ item.visitLocation }}</span>
                                            </div>
                                            <div class="info-row">
                                                <van-icon name="description" />
                                                <span>{{ item.reason }}</span>
                                            </div>
                                            <div class="create-time">申请时间：{{ item.createTime }}</div>
                                        </div>
                                    </div>
                                </template>
                            </van-cell>
                        </van-cell-group>
                    </div>
                </van-list>
            </van-pull-refresh>
        </div>
    </div>
</template>

<style scoped>
.audit-container {
    min-height: 100vh;
    background-color: #f7f8fa;
}

.list-container {
    padding: 12px;
}

.visit-card {
    margin-bottom: 12px;
}

.visitor-name {
    font-size: 16px;
    font-weight: bold;
    margin-right: 8px;
}

.status-tag {
    vertical-align: middle;
}

.info-row {
    display: flex;
    align-items: center;
    margin-top: 4px;
    color: #666;
    font-size: 14px;
}

.info-row .van-icon {
    margin-right: 4px;
    font-size: 14px;
}

.cell-content {
    display: flex;
    justify-content: space-between;
    align-items: flex-start;
    width: 100%;
}

.info-content {
    width: 100%;
}

.visitor-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 8px;
    width: 100%;
}

.visitor-info {
    display: flex;
    align-items: center;
}

.action-buttons {
    display: flex;
    gap: 8px;
}

.action-buttons .van-button {
    padding: 0 10px;
    opacity: 0.9;
}

.create-time {
    font-size: 12px;
    color: #999;
    margin-top: 8px;
}

:deep(.van-cell-group--inset) {
    margin: 0;
}

:deep(.van-nav-bar__title) {
    font-size: 16px;
    font-weight: bold;
}

:deep(.van-nav-bar .van-icon) {
    font-size: 20px;
}
</style>