     <script setup>
     import {onMounted, ref} from 'vue';
     import axios from 'axios';
     import {ElMessage, ElMessageBox} from "element-plus";

     const data = ref([]);
     const filteredData = ref([]);
     const queryParams = ref(
         {
           A: '',
           B: '',
           C: '',
           D: '',
           E: '',
           F: '',
           aa: '',
           bb: '',
           cc: '',
           dd: '',
           ee: ''
         });

     const addDialogVisible = ref(false);
     const editDialogVisible = ref(false);

     const addForm = ref({
        A: '',
        B: '',
        C: '',
        D: '',
        E: '',
        F: '',
        aa: '',
        bb: '',
        cc: '',
        dd: '',
        ee: ''
     });

     const editForm = ref({
        A: '',
        B: '',
        C: '',
        D: '',
        E: '',
        F: '',
        aa: '',
        bb: '',
        cc: '',
        dd: '',
        ee: ''
     });

     const editFormRules = ref({
       A: [
         {required: true, message: '请输入A', trigger: 'blur'}
       ],
       B: [
         {required: true, message: '请输入B', trigger: 'blur'}
       ],
       C: [
         {required: true, message: '请输入C', trigger: 'blur'}
       ],
       D: [
         {required: true, message: '请输入D', trigger: 'blur'}
       ],
       E: [
         {required: true, message: '请输入E', trigger: 'blur'}
       ],
       F: [
         {required: true, message: '请输入F', trigger: 'blur'}
       ],
       aa: [
         {required: true, message: '请输入aa', trigger: 'blur'}
       ],
       bb: [
         {required: true, message: '请输入bb', trigger: 'blur'}
       ],
       cc: [
         {required: true, message: '请输入cc', trigger: 'blur'}
       ],
       dd: [
         {required: true, message: '请输入dd', trigger: 'blur'}
       ],
       ee: [
         {required: true, message: '请输入ee', trigger: 'blur'}
       ]
     });

     const paginationOptions = ref({
       pageSize: 10,
       current: 1,
       total: 0
     });

     const getData = async () => {
       const response = await axios.get('http://localhost:8080/Aexcel/page',
           {
             params: {
               ...queryParams.value,
               current: paginationOptions.value.current,
               size: paginationOptions.value.pageSize,
             }
           });
       console.log(response);
       paginationOptions.value.total = response.data.data.total
       data.value = response.data.data.records;
       filteredData.value = data.value;
     };

     onMounted(() => {
       getData()
     });

     const openEditDialog = (row) => {
       editForm.value = JSON.parse(JSON.stringify(row))
       console.log(editForm.value)
       editDialogVisible.value = true;
     };

     const closeEditDialog = () => {
       editDialogVisible.value = false;
     };

     const saveEdit = () => {
       axios.put('http://localhost:8080/Aexcel', {...editForm.value}).then(res => {
         if (res.status == 200) {
           ElMessage.success("修改成功")
           getData()
         } else {
           ElMessage.error("修改失败")
         }
       }).catch(error => {
         console.log(error)
         ElMessage.error("修改失败")
       })
       closeEditDialog();
     };

     const addAexcel = () => {
       // 保存编辑逻辑，可以在这里向后端发送请求保存数据
       axios.post('http://localhost:8080/Aexcel', {...addForm.value}).then(res => {
         if (res.status == 200) {
           ElMessage.success("添加成功")
           getData()
         } else {
           ElMessage.error("添加失败")
         }
       }).catch(error => {
         console.log(error)
         ElMessage.error("添加失败")
       })
       addForm.value.A = ''
       addForm.value.B = ''
       addForm.value.C = ''
       addForm.value.D = ''
       addForm.value.E = ''
       addForm.value.F = ''
       addForm.value.aa = ''
       addForm.value.bb = ''
       addForm.value.cc = ''
       addForm.value.dd = ''
       addForm.value.ee = ''
       addDialogVisible.value = false
     }

     const remove = (row) => {
       ElMessageBox.confirm('确定删除该行吗？', '提示', {
         confirmButtonText: '确定',
         cancelButtonText: '取消',
         type: 'warning'
       }).then(() => {
         axios.delete('http://localhost:8080/Aexcel', {
           data: {
             'A': row.A
           }
         }).then(res => {
           if (res.status === 200) {
             ElMessage.success('删除成功');
             getData();
           }
         });
       }).catch(() => {
         // 用户取消删除操作
       });
     }

     const exportToExcel = (needData) => {
       axios({
         method: 'get',
         url: 'http://localhost:8080/Aexcel/excel',
         data: {},
         params: {
           needData: needData
         },
         responseType: 'blob'
       }).then(res => {
         let data = res.data
         if (!data) {
           return
         }
         let url = window.URL.createObjectURL(new Blob([data]))
         let a = document.createElement('a')
         a.style.display = 'none'
         a.href = url
         a.setAttribute('download', 'excel.xlsx')
         document.body.appendChild(a)
         a.click() //执行下载
         window.URL.revokeObjectURL(a.href)
         document.body.removeChild(a)
       }).catch((error) => {
         console.log(error)
       })
     };

     const uploadSuccess = () => {
       ElMessage.success("导入成功")
       getData()
     };

     const uploadError = () => {
       ElMessage.error("导入失败")
     };

     const reset = () => {
       queryParams.value.A = ""
       queryParams.value.B = ""
       queryParams.value.C = ""
       queryParams.value.D = ""
       queryParams.value.E = ""
       queryParams.value.F = ""
       queryParams.value.aa = ""
       queryParams.value.bb = ""
       queryParams.value.cc = ""
       queryParams.value.dd = ""
       queryParams.value.ee = ""
       getData()
     }

     const handleSizeChange = (val) => {
       paginationOptions.value.pageSize = val
       getData()
     }

     const handleCurrentChange = (val) => {
       paginationOptions.value.current = val
       getData()
     }

     </script>

     <template>
       <div>
         <el-card>
           <el-row :gutter="20">
             <el-col :span="5">
               <el-input v-model="queryParams.A" placeholder="A"></el-input>
             </el-col>
             <el-col :span="3">
               <el-input v-model="queryParams.B" placeholder="B"></el-input>
             </el-col>
             <el-col :span="3">
               <el-input v-model="queryParams.C" placeholder="C"></el-input>
             </el-col>
             <el-col :span="3">
               <el-input v-model="queryParams.D" placeholder="D"></el-input>
             </el-col>
             <el-col :span="3">
               <el-input v-model="queryParams.E" placeholder="E"></el-input>
             </el-col>
             <el-col :span="3">
               <el-input v-model="queryParams.F" placeholder="F"></el-input>
             </el-col>
             <el-col :span="3">
               <el-input v-model="queryParams.aa" placeholder="aa"></el-input>
             </el-col>
             <el-col :span="3">
               <el-input v-model="queryParams.bb" placeholder="bb"></el-input>
             </el-col>
             <el-col :span="3">
               <el-input v-model="queryParams.cc" placeholder="cc"></el-input>
             </el-col>
             <el-col :span="3">
               <el-input v-model="queryParams.dd" placeholder="dd"></el-input>
             </el-col>
             <el-col :span="3">
               <el-input v-model="queryParams.ee" placeholder="ee"></el-input>
             </el-col>
             <el-col :span="24" style="text-align: right; margin-top: 10px;">
               <el-button type="primary" @click="reset">重置</el-button>
               <el-button type="primary" @click="getData">搜索</el-button>
               <el-button type="success" @click="addDialogVisible = !addDialogVisible">添加</el-button>
               <el-button type="success" @click="exportToExcel(false)">下载Excel模版</el-button>
               <el-button type="info" @click="exportToExcel(true)">导出Excel</el-button>
               <el-upload style="display: inline"
                          class="upload-demo"
                          action="http://localhost:8080/Aexcel/excel"
                          :on-success="uploadSuccess"
                          :on-error="uploadError"
                          :show-file-list="false"
               >
                 <el-button type="success">上传Excel</el-button>
               </el-upload>
             </el-col>
           </el-row>

         </el-card>


         <el-card>
           <el-table
               :data="filteredData"
               :default-sort="{prop: 'A', order: 'ascending'}"
               :row-key="row => row.A"
               :pagination="paginationOptions"
           >
             <el-table-column label="A" prop="A"></el-table-column>
             <el-table-column label="B" prop="B"></el-table-column>
             <el-table-column label="C" prop="C"></el-table-column>
             <el-table-column label="D" prop="D"></el-table-column>
             <el-table-column label="E" prop="E"></el-table-column>
             <el-table-column label="F" prop="F"></el-table-column>
             <el-table-column label="aa" prop="aa"></el-table-column>
             <el-table-column label="bb" prop="bb"></el-table-column>
             <el-table-column label="cc" prop="cc"></el-table-column>
             <el-table-column label="dd" prop="dd"></el-table-column>
             <el-table-column label="ee" prop="ee"></el-table-column>
             <el-table-column label="操作">
               <template #default="{ row }">
                 <el-button type="primary" size="default" @click="openEditDialog(row)">编辑</el-button>
                 <el-button type="danger" size="default" @click="remove(row)">删除</el-button>
               </template>
             </el-table-column>
           </el-table>
         </el-card>
         <el-card>
           <el-pagination
               @size-change="handleSizeChange"
               @current-change="handleCurrentChange"
               :current-page="queryParams.current"
               :page-sizes="[5, 10, 20, 50]"
               :page-size="10"
               layout="total, sizes, prev, pager, next, jumper"
               :total="paginationOptions.total">
           </el-pagination>
         </el-card>

         <el-dialog
             v-model="addDialogVisible"
             title="添加实例"
             width="40%"
             @close="closeEditDialog"
         >
           <!-- 编辑表单内容 -->
           <el-form :model="addForm" :rules="editFormRules">
             <el-form-item label="A" prop="A">
               <el-input v-model="addForm.A"></el-input>
             </el-form-item>
             <el-form-item label="B" prop="B">
               <el-input v-model="addForm.B"></el-input>
             </el-form-item>
             <el-form-item label="C" prop="C">
               <el-input v-model="addForm.C"></el-input>
             </el-form-item>
             <el-form-item label="D" prop="D">
               <el-input v-model="addForm.D"></el-input>
             </el-form-item>
             <el-form-item label="E" prop="E">
                <el-input v-model="addForm.E"></el-input>
             </el-form-item>
             <el-form-item label="F" prop="F">
                <el-input v-model="addForm.F"></el-input>
             </el-form-item>
             <el-form-item label="aa" prop="aa">
                <el-input v-model="addForm.aa"></el-input>
             </el-form-item>
             <el-form-item label="bb" prop="bb">
                <el-input v-model="addForm.bb"></el-input>
             </el-form-item>
             <el-form-item label="cc" prop="cc">
                <el-input v-model="addForm.cc"></el-input>
             </el-form-item>
             <el-form-item label="dd" prop="dd">
                <el-input v-model="addForm.dd"></el-input>
             </el-form-item>
             <el-form-item label="ee" prop="ee">
                <el-input v-model="addForm.ee"></el-input>
             </el-form-item>
           </el-form>

           <span slot="footer" class="dialog-footer">
             <el-button @click="addDialogVisible = !addDialogVisible">取 消</el-button>
             <el-button type="primary" @click="Aexcel">确 定</el-button>
           </span>
         </el-dialog>

         <el-dialog
             v-model="editDialogVisible"
             title="编辑实例信息"
             width="40%"
             @close="closeEditDialog"
         >
           <!-- 编辑表单内容 -->
           <el-form :model="editForm" :rules="editFormRules">
             <el-form-item label="A" prop="A">
               <el-input disabled v-model="editForm.A"></el-input>
             </el-form-item>
             <el-form-item label="B" prop="B">
               <el-input v-model="editForm.B"></el-input>
             </el-form-item>
             <el-form-item label="C" prop="C">
               <el-input v-model="editForm.C"></el-input>
             </el-form-item>
             <el-form-item label="D" prop="D">
               <el-input v-model="editForm.D"></el-input>
             </el-form-item>
             <el-form-item label="E" prop="E">
               <el-input v-model="editForm.E"></el-input>
             </el-form-item>
             <el-form-item label="F" prop="F">
               <el-input v-model="editForm.F"></el-input>
             </el-form-item>
             <el-form-item label="aa" prop="aa">
               <el-input v-model="editForm.aa"></el-input>
             </el-form-item>
             <el-form-item label="bb" prop="bb">
               <el-input v-model="editForm.bb"></el-input>
             </el-form-item>
             <el-form-item label="cc" prop="cc">
               <el-input v-model="editForm.cc"></el-input>
             </el-form-item>
             <el-form-item label="dd" prop="dd">
               <el-input v-model="editForm.dd"></el-input>
             </el-form-item>
             <el-form-item label="ee" prop="ee">
               <el-input v-model="editForm.ee"></el-input>
             </el-form-item>
           </el-form>

           <span slot="footer" class="dialog-footer">
             <el-button @click="closeEditDialog">取 消</el-button>
             <el-button type="primary" @click="saveEdit">确 定</el-button>
           </span>
         </el-dialog>


       </div>
     </template>

     <style scoped>
     /* 搜索框和按钮样式 */
     .el-input {
       margin-bottom: 10px;
     }

     .el-button {
       margin-left: 10px;
     }

     /* 表格样式 */
     .el-table {
       width: 100%;
       margin-top: 20px;
     }

     /* 表头样式 */
     .el-table__header th {
       background-color: #f5f7fa;
     }

     /* 行样式 */
     .el-table__row {
       transition: background-color 0.3s;
     }

     .el-table__row:hover {
       background-color: #f0f9eb;
     }

     /* 分页样式 */
     .el-pagination {
       margin-top: 20px;
     }
     </style>
