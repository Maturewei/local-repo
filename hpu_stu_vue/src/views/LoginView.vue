<template>
  <div>
  <el-row>
    <el-col :span="6":offset="9">
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="用户名">
        <el-input v-model="form.username"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="form.password"  show-password></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">登录</el-button>
      </el-form-item>
    </el-form>
    </el-col>
  </el-row>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: "LoginView",
  //主要是定义一些上面html使用的数据
  data() {
    return {
      form: {
        username: '',
        password: ''
      }
    }
  },
  // methods 定义上面html中事件所触发的方法
  methods: {
    onSubmit() {
      // 发请求，到后端
      // 参数1 是路径
      // 参数2 是发给后端的数据
      // then 请求成功，后端返回的数据
      axios.post("http://localhost:8888/login",this.form)
           .then((res) =>{
             console.log("后端返回得到的结果",res);
             if(res.data != '') {
               this.$message({
                 showClose: true,
                 message: '恭喜你，登录成功',
                 type: 'success'
               });
               //登录时存储人名
               window.localStorage.setItem("username",res.data.username)
               //路由切换（跳转）AdminView
               this.$router.push("/admin")
             }
             else{
               this.$message({
                 showClose: true,
                 message: '用户或密码错误',
                 type: 'warning'
               });
             }
               }

           )
    }
  }
}
</script>

<style scoped>

</style>