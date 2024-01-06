<template>
  <div>
    <el-card>
      <div style="text-align: left">
        <el-button type="success" @click="addDialogVisible=true"> 添加 </el-button>
        <el-dialog
            title="添加学生信息"
            :visible.sync="addDialogVisible"
            width="30%">

          <el-form ref="stu" :model="stu" label-width="80px">
            <el-form-item label="学号">
              <el-input v-model="stu.id"></el-input>
            </el-form-item>

            <el-form-item label="用户名">
              <el-input v-model="stu.username"></el-input>
            </el-form-item>

            <el-form-item label="密码">
              <el-input v-model="stu.password"></el-input>
            </el-form-item>

            <el-form-item label="年龄">
              <el-input v-model="stu.age"></el-input>
            </el-form-item>

            <el-form-item label="性别">
              <el-radio-group v-model="stu.sex">
                <el-radio :label="0">男</el-radio>
                <el-radio :label="1">女</el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item label="分数">
              <el-input v-model="stu.score"></el-input>
            </el-form-item>

            <el-form-item label="生日">
              <el-col :span="11">
                <el-date-picker type="date" placeholder="选择日期" v-model="stu.birthday" style="width: 100%;"></el-date-picker>
              </el-col>
            </el-form-item>

          </el-form>
          <span slot="footer" class="dialog-footer">
    <el-button @click="addDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="submitAdd()">确 定</el-button>
  </span>
        </el-dialog>
      </div>
      <!--el-table表格组件-->
      <!--:data=tableData 是绑定表格数据,在下方data中定义,是一个数组类型-->
      <!--数组是对象,下面prop就是对象的属性-->
      <el-table :data="tableData" border style="width: 100%" max-height="480">
        <!--一个el-table-column,就是一列-->
        <!--prop的值是根据后端返回的数据定的-->
        <el-table-column prop="id" label="学号" ></el-table-column>
        <el-table-column prop="username" label="姓名" >
          <template slot-scope="scope">
          <el-tag size="medium">{{ scope.row.username }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="password" label="密码"></el-table-column>
        <el-table-column prop="age" label="年龄"></el-table-column>
        <el-table-column prop="sex" label="性别">
          <template slot-scope="scope">
            <span style="margin-left: 10px">
              <span v-if="scope.row.sex == 0">男</span>
              <span v-if="scope.row.sex == 1">女</span>
            </span>
          </template>
        </el-table-column>
        <el-table-column prop="score" label="分数"></el-table-column>
        <el-table-column prop="birthday" label="生日"></el-table-column>

        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
                size="mini"
                type="warning"
                @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            <el-button
                size="mini"
                type="danger"
                @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>

      </el-table>
    </el-card>
    <el-card>
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5, 10, 15, 20]"
          :page-size="10"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
      <el-button type="text" @click="editDialogVisible = true"></el-button>
      <el-dialog
          title="更新学生信息"
          :visible.sync="editDialogVisible"
          width="30%">

        <el-form ref="stu" :model="stu" label-width="80px">
          <el-form-item label="学号">
            <el-input v-model="stu.id"></el-input>
          </el-form-item>

          <el-form-item label="用户名">
            <el-input v-model="stu.username"></el-input>
          </el-form-item>

          <el-form-item label="密码">
            <el-input v-model="stu.password"></el-input>
          </el-form-item>

          <el-form-item label="年龄">
            <el-input v-model="stu.age"></el-input>
          </el-form-item>

          <el-form-item label="性别">
            <el-radio-group v-model="stu.sex">
              <el-radio :label="0">男</el-radio>
              <el-radio :label="1">女</el-radio>
            </el-radio-group>
          </el-form-item>

          <el-form-item label="分数">
            <el-input v-model="stu.score"></el-input>
          </el-form-item>

          <el-form-item label="生日">
            <el-col :span="11">
              <el-date-picker type="date" placeholder="选择日期" v-model="stu.birthday" style="width: 100%;"></el-date-picker>
            </el-col>
          </el-form-item>

        </el-form>
        <span slot="footer" class="dialog-footer">
    <el-button @click="editDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="submitEdit()">确 定</el-button>
  </span>
      </el-dialog>
    </el-card>
  </div>
</template>

<script>
// 导入axios
import axios from "axios";
export default {
  name: "StuInfoView",
  // data是定义的数据变量
  data() {
    return {
      // 此处就是表格中的数据,现在是写固定的
      // 后续就需要从数据库查询返回到前端
      // 前端再展现
      tableData: [],
      currentPage: 1,
      total: 0,   //默认总条数是0
      pageSize: 10,
      editDialogVisible:false,   //默认对话框不可见
      addDialogVisible:false,
      stu:{
        id:"",
        username:"",
        password:"",
        age:"",
        sex:"",
        score:"",
        birthday:""

      }
    }
  },
  // vue声明周期函数,在vue对象创建完毕时自动触发
  created(){
    // 在此处发请求
    this.getData();
  },

  methods: {
    //定义一个函数专门用于查询分页，解决了代码复用的问题
    getData(){
      axios.get("http://localhost:8888/stu/list",{
        params:{
          currentPage : this.currentPage,
          pageSize : this.pageSize
        }
      })
          .then(res => {
            console.log("后端返回的数据",res)
            // 将后端数据赋值给表格数组
            this.tableData = res.data.list;
            this.pageSize = res.data.pageSize;
            this.total = res.data.total;
            this.currentPage = res.data.pageNum;
          })
    },
    // 页码的处理函数
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageSize = val;
      this.getData();
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.currentPage = val;
      this.getData();
    },
    // 编辑删除的处理函数
    handleEdit(index, row) {
      console.log(index, row);
      this.editDialogVisible = true; // 对话框可见
      this.stu = row;
    },
    // 处理删除
    handleDelete(index, row) {
      console.log(index, row);
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 发删除请求到后端
        axios.get("http://localhost:8888/stu/del",{
          params:{
            id:row.id
          }
        }).then(res => {
          if (res.data) {
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            // 重新查询最新数据
            this.getData();
          } else {
            this.$message({
              type: 'error',
              message: '删除失败!'
            });
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },

    // 处理更新请求
    submitEdit(){
      console.log(this.stu);
      // 发请求,带数据,到后端
      axios.post("http://localhost:8888/stu/edit",this.stu)
          .then(res => {
            console.log("更新后,后端返回", res)
            if (res.data) {
              // 弹框提示
              this.$message({
                message: '更新成功',
                type: 'success',
              });
              // 对话框消失
              this.editDialogVisible = false;
              // 重新查询
              this.getData();
            } else {
              // 弹框提示
              this.$message({
                message: '更新失败',
                type: 'error',
              });
            }
          })
    },
    // 处理添加请求
    submitAdd(){
      console.log(this.stu);
      // 发请求,带数据,到后端
      axios.post("http://localhost:8888/stu/add",this.stu)
          .then(res => {
            console.log("后端返回的结果", res)
            if (res.data) {
              // 弹框提示
              this.$message({
                message: '添加成功',
                type: 'success',
              });
              // 对话框消失
              this.addDialogVisible = false;
              // 重新查询
              this.getData();
            } else {
              // 弹框提示
              this.$message({
                message: '添加失败',
                type: 'error',
              });
            }
          })
    }
  },
}

</script>


<style scoped>
.text {
  font-size: 14px;
}

.item {
  padding: 18px;
}

.box-card {
  width: 1000px;
}
</style>