<template>
    <div class="add-action">
        <div class="add-action-header">
            <mt-header title="发布活动">
                <router-link to="/" slot="left">
                    <mt-button icon="back">返回</mt-button>
                </router-link>
            </mt-header>
        </div>
        <div class="add-action-content">
            <mt-field label="主题" placeholder="请输入主题" v-model="theme"></mt-field>
            <mt-field label="地点" placeholder="请输入地点" type="tel" v-model="location"></mt-field>
            <a class="mint-cell mint-field">
                <div class="mint-cell-wrapper">
                    <div>
                        <span class="mint-cell-text">开始时间</span>
                    </div>
                    <div @click="openPicker" class="start-time-mint-cell-value">
                        {{time | formatDate}}
                    </div>
                </div>
            </a>
            <mt-field label="手机号" placeholder="请输入手机号" type="tel" v-model="phone"></mt-field>
            <mt-field label="人均消费" placeholder="请输入人均消费" type="number" v-model="consumption"></mt-field>
            <mt-field label="活动介绍" placeholder="活动介绍" type="textarea" rows="4" v-model="introduction"></mt-field>
            <el-upload
                action="//up.qbox.me/"
                list-type="picture-card"
                :file-list="fileList"
                :on-change="handleChange"
                :data="uptoken"
                :before-upload="beforeUpload">
                <i class="el-icon-plus"></i>
                </el-upload>
                <el-dialog :visible.sync="dialogVisible">
                <img width="100%" :src="dialogImageUrl" alt="">
            </el-dialog>
            <mt-button class="submit-content" type="danger" @click="submitAction">提交</mt-button>
            <mt-datetime-picker
                ref="picker"
                type="datetime"
                :accept="accept"
                v-model="time"
                year-format="{value} 年"
                month-format="{value} 年"
                date-format="{value} 月"
                hour-format="{value} 时"
                minute-format="{value} 分"
                >
            </mt-datetime-picker>
        </div>
    </div>
</template>

<script>
import { Toast } from 'mint-ui';
import api from './../fetch/api.js';
import { formatDate } from './../components/date.js';
export default {
    filters: {                    //时间转换
        formatDate(time) {
            var date = new Date(time);
            return formatDate(date, 'yyyy年MM月dd日 hh时mm分');
        }
    },
    data() {
        return {
            accept: "image/jpeg,image/gif,image/png,image/bmp",
            theme: '',
            location: '',
            phone: '',
            consumption: '',
            introduction: '',
            time: new Date(),
            fileList: [],
            dialogImageUrl: '',
            dialogVisible: false,
            uploadImg: '',
            uptoken: {
                token: '',
                key: ""
            }
        }
    },
    mounted: function () {
        var that = this;
        /**
         * 获取七牛云token
         */
        api.getUpToken().then(function (res) {
            that.uptoken.token = res.data;
        })
    },
    methods: {
        openPicker() {
            this.$refs.picker.open();
        },
        submitAction() {
            var data = {
                'theme': this.theme,
                'location': this.location,
                'phone': this.phone,
                'time': this.time,
                'consumption': this.consumption,
                'introduction': this.introduction,
                'dateActionImg': this.uploadImg
            }
            api.submitAction(data).then(res => {
                if (res.data.status == 0) {
                    Toast({
                        message: '添加成功',
                        position: 'middle',
                        duration: 5000,
                        iconClass: 'icon-tip'
                    });
                    this.$router.push('/home')
                }
            })
        },
        //上传图片
        handleChange(file, fileList) {
            console.log(fileList);
            this.uploadImg = fileList[0].name;
            console.log(this.uploadImg);
            // console.log(dialogImageUrl);
        },
        beforeUpload (file) {
            this.uptoken.key = file.name;
        },

    }
}
</script>

<style lang="scss">
@import url("//unpkg.com/element-ui@2.4.9/lib/theme-chalk/index.css");
.add-action {
    .add-action-header {
        .mint-header {
            background: pink;
        }
    }
    .add-action-content {
        .submit-content {
            width: 96%;
            display: block;
            margin: 10px auto;
            background: pink;
        }
        .icon-tip {
            color: pink;
        }
    }
    .start-time-mint-cell-value {
        margin-left: 39px;
        color: #888;
        text-decoration: none;
    }
}
</style>

