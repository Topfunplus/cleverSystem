<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="姓名，访客的真实姓名" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入姓名，访客的真实姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入手机号，访客的联系电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="身份证号" prop="idCard">
        <el-input
          v-model="queryParams.idCard"
          placeholder="请输入身份证号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="访客单位" prop="visitorUnit">
        <el-input
          v-model="queryParams.visitorUnit"
          placeholder="请输入访客单位，访客所在单位名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否开车，0表示未开车，1表示开车，默认未开车" prop="isCar">
        <el-input
          v-model="queryParams.isCar"
          placeholder="请输入是否开车，0表示未开车，1表示开车，默认未开车"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车牌号，访客的车牌号码，开车时需要填写" prop="carPlate">
        <el-input
          v-model="queryParams.carPlate"
          placeholder="请输入车牌号，访客的车牌号码，开车时需要填写"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="访客时间，访客计划进入的日期" prop="visitTime">
        <el-date-picker clearable
                        v-model="queryParams.visitTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择访客时间，访客计划进入的日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="访问时长，访客预定的访问时间，单位：分钟" prop="visitDuration">
        <el-input
          v-model="queryParams.visitDuration"
          placeholder="请输入访问时长，访客预定的访问时间，单位：分钟"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="申请时间，访客提交申请的时间" prop="requestTime">
        <el-date-picker clearable
                        v-model="queryParams.requestTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择申请时间，访客提交申请的时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="一级审核时间，第一次审核的时间，表示审核员对申请的时间戳" prop="firstApprovalTime">
        <el-date-picker clearable
                        v-model="queryParams.firstApprovalTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择一级审核时间，第一次审核的时间，表示审核员对申请的时间戳">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="二级审核时间，第二次审核的时间，表示二级审核员的审核时间" prop="secondApprovalTime">
        <el-date-picker clearable
                        v-model="queryParams.secondApprovalTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择二级审核时间，第二次审核的时间，表示二级审核员的审核时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:visitor_info:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:visitor_info:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:visitor_info:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:visitor_info:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="infoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="访客ID，主键，唯一标识每个访客" align="center" prop="id"/>
      <el-table-column label="姓名，访客的真实姓名" align="center" prop="name"/>
      <el-table-column label="手机号，访客的联系电话" align="center" prop="phone"/>
      <el-table-column label="身份证号，访客的身份证号码，用于身份识别" align="center" prop="idCard"/>
      <el-table-column label="访客单位，访客所在单位名称" align="center" prop="visitorUnit"/>
      <el-table-column label="到访理由，访客到工厂的目的或理由" align="center" prop="visitReason"/>
      <el-table-column label="身份证正面照片，存储身份证正面的图像" align="center" prop="idCardFrontImage" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.idCardFrontImage" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="身份证反面照片，存储身份证背面的图像" align="center" prop="idCardBackImage" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.idCardBackImage" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="人脸照片，访客的脸部照片，用于闸机识别" align="center" prop="faceImage" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.faceImage" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="是否开车，0表示未开车，1表示开车，默认未开车" align="center" prop="isCar"/>
      <el-table-column label="车牌号，访客的车牌号码，开车时需要填写" align="center" prop="carPlate"/>
      <el-table-column label="访客时间，访客计划进入的日期" align="center" prop="visitTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.visitTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="访问时长，访客预定的访问时间，单位：分钟" align="center" prop="visitDuration"/>
      <el-table-column label="申请时间，访客提交申请的时间" align="center" prop="requestTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.requestTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审核状态，表示当前申请的审批状态" align="center" prop="status"/>
      <el-table-column label="一级审核时间，第一次审核的时间，表示审核员对申请的时间戳" align="center"
                       prop="firstApprovalTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.firstApprovalTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="一级审核意见，审核员对申请的意见或说明" align="center" prop="firstApprovalOpinion"/>
      <el-table-column label="二级审核时间，第二次审核的时间，表示二级审核员的审核时间" align="center"
                       prop="secondApprovalTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.secondApprovalTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="二级审核意见，二级审核员的审核意见或说明" align="center" prop="secondApprovalOpinion"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:visitor_info:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:visitor_info:remove']"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改用于存储访客相关信息的对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="姓名，访客的真实姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入姓名，访客的真实姓名"/>
        </el-form-item>
        <el-form-item label="手机号，访客的联系电话" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入手机号，访客的联系电话"/>
        </el-form-item>
        <el-form-item label="身份证号，访客的身份证号码，用于身份识别" prop="idCard">
          <el-input v-model="form.idCard" placeholder="请输入身份证号，访客的身份证号码，用于身份识别"/>
        </el-form-item>
        <el-form-item label="访客单位，访客所在单位名称" prop="visitorUnit">
          <el-input v-model="form.visitorUnit" placeholder="请输入访客单位，访客所在单位名称"/>
        </el-form-item>
        <el-form-item label="到访理由，访客到工厂的目的或理由" prop="visitReason">
          <el-input v-model="form.visitReason" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="身份证正面照片，存储身份证正面的图像" prop="idCardFrontImage">
          <image-upload v-model="form.idCardFrontImage"/>
        </el-form-item>
        <el-form-item label="身份证反面照片，存储身份证背面的图像" prop="idCardBackImage">
          <image-upload v-model="form.idCardBackImage"/>
        </el-form-item>
        <el-form-item label="人脸照片，访客的脸部照片，用于闸机识别" prop="faceImage">
          <image-upload v-model="form.faceImage"/>
        </el-form-item>
        <el-form-item label="是否开车，0表示未开车，1表示开车，默认未开车" prop="isCar">
          <el-input v-model="form.isCar" placeholder="请输入是否开车，0表示未开车，1表示开车，默认未开车"/>
        </el-form-item>
        <el-form-item label="车牌号，访客的车牌号码，开车时需要填写" prop="carPlate">
          <el-input v-model="form.carPlate" placeholder="请输入车牌号，访客的车牌号码，开车时需要填写"/>
        </el-form-item>
        <el-form-item label="访客时间，访客计划进入的日期" prop="visitTime">
          <el-date-picker clearable
                          v-model="form.visitTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择访客时间，访客计划进入的日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="访问时长，访客预定的访问时间，单位：分钟" prop="visitDuration">
          <el-input v-model="form.visitDuration" placeholder="请输入访问时长，访客预定的访问时间，单位：分钟"/>
        </el-form-item>
        <el-form-item label="申请时间，访客提交申请的时间" prop="requestTime">
          <el-date-picker clearable
                          v-model="form.requestTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择申请时间，访客提交申请的时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="一级审核时间，第一次审核的时间，表示审核员对申请的时间戳" prop="firstApprovalTime">
          <el-date-picker clearable
                          v-model="form.firstApprovalTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择一级审核时间，第一次审核的时间，表示审核员对申请的时间戳">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="一级审核意见，审核员对申请的意见或说明" prop="firstApprovalOpinion">
          <el-input v-model="form.firstApprovalOpinion" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="二级审核时间，第二次审核的时间，表示二级审核员的审核时间" prop="secondApprovalTime">
          <el-date-picker clearable
                          v-model="form.secondApprovalTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择二级审核时间，第二次审核的时间，表示二级审核员的审核时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="二级审核意见，二级审核员的审核意见或说明" prop="secondApprovalOpinion">
          <el-input v-model="form.secondApprovalOpinion" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listVisitorInfo,
  getVisitorInfo,
  delVisitorInfo,
  addVisitorInfo,
  updateVisitorInfo
} from "@/api/system/visitor_info";

export default {
  name: "Info",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 用于存储访客相关信息的表格数据
      infoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        phone: null,
        idCard: null,
        visitorUnit: null,
        visitReason: null,
        idCardFrontImage: null,
        idCardBackImage: null,
        faceImage: null,
        isCar: null,
        carPlate: null,
        visitTime: null,
        visitDuration: null,
        requestTime: null,
        status: null,
        firstApprovalTime: null,
        firstApprovalOpinion: null,
        secondApprovalTime: null,
        secondApprovalOpinion: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          {required: true, message: "姓名，访客的真实姓名不能为空", trigger: "blur"}
        ],
        phone: [
          {required: true, message: "手机号，访客的联系电话不能为空", trigger: "blur"}
        ],
        idCard: [
          {required: true, message: "身份证号，访客的身份证号码，用于身份识别不能为空", trigger: "blur"}
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询用于存储访客相关信息的列表 */
    getList() {
      this.loading = true;
      listVisitorInfo(this.queryParams).then(response => {
        this.infoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        name: null,
        phone: null,
        idCard: null,
        visitorUnit: null,
        visitReason: null,
        idCardFrontImage: null,
        idCardBackImage: null,
        faceImage: null,
        isCar: null,
        carPlate: null,
        visitTime: null,
        visitDuration: null,
        requestTime: null,
        status: null,
        firstApprovalTime: null,
        firstApprovalOpinion: null,
        secondApprovalTime: null,
        secondApprovalOpinion: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加用于存储访客相关信息的";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getVisitorInfo(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改用于存储访客相关信息的";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateVisitorInfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addVisitorInfo(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除用于存储访客相关信息的编号为"' + ids + '"的数据项？').then(function () {
        return delVisitorInfo(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/info/export', {
        ...this.queryParams
      }, `info_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
