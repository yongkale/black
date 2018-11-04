<template>
    <section style="z-index:2100">
        <div>
            <mt-header title="选择所在城市">
                <router-link to="/" slot="left">
                    <mt-button @click="backToHome()" icon="back">返回</mt-button>
                </router-link>
            </mt-header>
        </div>
        <mt-search v-model="searchData" placeholder="输入你所在的城市">
            <ul style="background: #fff;">
                <li class="city-cell" v-for="item in searchCityData" @click="changecity(item.Name)" :key="item">
                    {{item.Name}}
                </li>
            </ul>
        </mt-search>
        <div v-if="isSerachCity">
        <mt-index-list>
            <li class="mint-indexsection">
                <p class="mint-indexsection-index">定位/常用</p>
                <ul class="hot-city clearfix" style="padding-bottom:0">
                    <li class="current-place active mint-indexsection-index-common" @click="changecity(currentCity)">
                        <img style="height: 30px;" class='localtion-svg' src="./../assets/img/location.svg" alt="ff" />
                        <span class="current-city">{{currentCity}}</span>
                    </li>
                </ul>
            </li>
            <li class="mint-indexsection mint-indexsection-hot-city">
                <p class="mint-indexsection-index">热门城市</p>
                <ul class="hot-city clearfix">
                    <li class="hot-city-cell" v-for="item in Hcity" :key="item.Code" @click="changecity(item.Name)">{{item.Name}}</li>
                </ul>
            </li>
            <div style="clear:both"></div>
            <mt-index-section v-for="(item,key) in resolveCityData" :key="key" :index="key">
                    <li class="city-cell" v-for="it in item" :key="it.Code" @click="changecity(it.Name)">
                        {{it.Name}}
                    </li>
                </mt-index-section>

        </mt-index-list>
        </div>
        <div id="allmap"></div>
    </section>
</template>

<script>
import { mapMutations, mapState } from 'vuex'; 
export default {
    props: {
        Hcity: {
            type: Array,
            default: []
        },
        Cdata: {
            type: Object,
            default: () => ({})
        },
        Tcity: {
            type: String,
            default: ''
        },
        currentCity: {
            type: String,
            default: '上海'
        }
    },
    data() {
        return {
            searchData: '',
            searchCityData: [],
            isSerachCity: true,
        }
    },
    mounted() {

    },
    computed: {
        ...mapState('base',['targetCity', 'startCity', 'endCity']),
        resolveCityData() {
            let ob = {}
            this.Cdata.Cities.forEach((cities) => {
                let key = cities.Code[0];
                if (ob[key] == undefined) {
                    ob[key] = new Array();
                }
                ob[key].push(cities);
            });
            return ob;
        },

    },
    methods: {
        ...mapMutations('base',['changeStaticState']),
        changecity(val) {
            this.searchData = '';
            this.changeStaticState({
                name: 'showCity',
                newVal: false
            });
            this.changeStaticState({
                name: this.Tcity,
                newVal: val
            });
        },
        backToHome() {
            this.searchData = ''
            this.changeStaticState({
                name: 'showCity',
                newVal: false
            });
        },
        offPopup() {
            this.changeStaticState({
                name: 'showCity',
                newVal: false
            })
        }

    },
    watch: {
        searchData(newValue) {
            if (newValue === '') {
                this.isSerachCity = true;
                this.searchCityData = [];
            } else {
                this.isSerachCity = false;
                this.searchCityData = this.Cdata.Cities.filter(cities => cities.CityCode.toLowerCase().indexOf(this.searchData.toLowerCase()) !== -1 || cities.Code.toLowerCase().indexOf(this.searchData.toLowerCase()) !== -1 || cities.Name.indexOf(this.searchData) !== -1);
            }
        }
    },
}
</script>

<style  lang="scss">

section {
    .mint-header {
        background: pink;
    }
    .mint-search-list {
        margin-top: 20px;
    }
    .mint-searchbar {
        width: 95%;
        background: #fff;
        .mint-searchbar-inner {
            border-radius: 62px;
            outline: none;
            border: 1px solid pink;
        }
    }
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: #fff;
    ul {
        padding: 0;
        list-style: none;
        .mint-cell {
            border-bottom: 1px solid #ddd;
        }
        .city-cell {
            line-height: 2.0741rem;
            border-bottom: 1px solid #ddd;
            padding: 0 10px;
            font-size: 16px;
            position: relative;
        }
    }
    .hot-city {
        padding: .2222rem/* 24/108 */
        .1852rem/* 20/108 */;
        padding-top: 0;
        .hot-city-cell {
        margin: 0 5px;
            float: left;
            width: 30%;
            line-height: 1.8333rem/* 90/108 */
            ;
            text-align: center;
            margin-right: .1852rem/* 20/108 */
            ;
            margin-bottom: .2222rem/* 24/108 */
            ;
            border: 1px solid #e3e3e3;
            background: #fff;
            font-size: 16px;
            &.active {
                border-color: #fcca7e;
            }
            &.current-place i {
                color: #fcca7e;
                margin-left: -8px;
            }
        }
    }
    .search-back{
        position: absolute;
        top:0;
        left: 10px;
        background-color: transparent;
        border: 0;
        box-shadow: none;
        color: inherit;
        display: inline-block;
        padding: 0;
        font-size: inherit;
        z-index: 101;
        i{
            font-size: 20px;
            color: #fff;
        }
    }
    .mint-search {
        height: 40px;
        .mint-searchbar-cancel {
            display: none;
        }
    }
    .mint-indexsection-index {
        background: #fff;
        display: block;
    }
    .mint-indexsection-hot-city {
        display: block;
        background: #fff;
        float: none;
    }
    .mint-indexsection-index-common {
        float: none;
        border: 1px solid #fcca7e;
        width: 90px;
        height: 30px;
        margin-left: 5px;
    }
    .localtion-svg {
        width: 98px;
        height: 30px;
    }
    .mint-indexlist-navitem {
        height: 15px;
    }
    .mint-indexlist-nav {
        top: -47px;
    }
    .current-city {
        position: relative;
        left: 101px;
        top: -26px;
    }
    .mint-searchbar-core {
        margin-left: 5px;
    }
}
</style>
