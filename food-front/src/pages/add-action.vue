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
            <div @click="openPicker" >
                <mt-field label="时间" placeholder="请选择时间" type="datetime" v-model="time"></mt-field>
            </div>
            <mt-field label="手机号" placeholder="请输入手机号" type="tel" v-model="phone"></mt-field>
            <mt-field label="人均消费" placeholder="请输入人均消费" type="number" v-model="consumption"></mt-field>
            <mt-field label="活动介绍" placeholder="活动介绍" type="textarea" rows="4" v-model="introduction"></mt-field>
            <mt-button class="submit-content" type="danger" @click="submitAction">提交</mt-button>
            <mt-datetime-picker
                ref="picker"
                type="datetime"
                v-model="time">
            </mt-datetime-picker>
        </div>
    </div>
</template>

<script>
import { Toast } from 'mint-ui';
import api from './../fetch/api.js';
export default {
    data() {
        return {
            theme: '',
            location: '',
            phone: '',
            consumption: '',
            introduction: '',
            time: ''
        }
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
                'introduction': this.introduction
            }
                Toast({
                        message: '添加成功',
                        position: 'middle',
                        duration: 5000,
                        iconClass: 'icon-tip'
                    });
            api.submitAction(data).then(res => {
                if (res.status == 0) {
                    Toast({
                        message: '添加成功',
                        position: 'middle',
                        duration: 5000,
                        iconClass: 'icon-tip'
                    });
                }
            })
        }
    }
}
</script>

<style lang="scss">
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
}
</style>

