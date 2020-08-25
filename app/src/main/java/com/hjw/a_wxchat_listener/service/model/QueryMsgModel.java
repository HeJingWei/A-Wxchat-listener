package com.hjw.a_wxchat_listener.service.model;

import java.util.List;

public class QueryMsgModel {

    /**
     * msg : {"appinfo":{"isforceupdate":1,"appname":"微信支付","version":0},"fromusername":"gh_3dfda90e39d6","appmsg":{"appattach":{"totallen":0,"fileext":"","cdnthumbaeskey":"","aeskey":"","cdnthumburl":"","attachid":""},"action":"","url":"https://wx.tenpay.com/cgi-bin/mmpayweb-bin/jumpuserroll?trans_id=100010710120052500064222108623480836&accid=085e9858e34ce9d3aa34b03bd","thumburl":"","appid":"","mmreader":{"forbid_forward":0,"category":{"name":"微信支付","count":1,"item":{"recommendation":"","del_flag":0,"weapp_username":"","url":"https://wx.tenpay.com/cgi-bin/mmpayweb-bin/jumpuserroll?trans_id=100010710120052500064222108623480836&accid=085e9858e34ce9d3aa34b03bd","weapp_state":0,"cover_1_1":"","is_pay_subscribe":0,"music_source":0,"weapp_path":"","pic_urls":"","digest":"付款金额￥0.01\n收款方🎾 🎾 🎾 🎾 🎾 🎾\n支付方式零钱\n交易状态支付成功，对方已收款","vid":"","tweetid":"","pub_time":1590398008,"comment_topic_id":0,"video_height":0,"shorturl":"","sources":{"source":{"name":"微信支付"}},"itemshowtype":4,"cover":"","native_url":"","play_url":"","title":"微信支付凭证","weapp_version":0,"longurl":"","pic_num":0,"appmsg_like_type":0,"template_op_type":0,"contentattr":0,"cover_235_1":"","player":"","video_width":0,"fileid":0,"show_complaint_button":0,"play_length":0,"styles":{"style":[{"range":"{4,5}","color":"#000000","font":"s"},{"range":"{13,11}","color":"#000000","font":"s"},{"range":"{29,2}","color":"#000000","font":"s"},{"range":"{36,10}","color":"#000000","font":"s"}],"topColor":""}},"type":0,"topnew":{"digest":"付款金额￥0.01\n收款方🎾 🎾 🎾 🎾 🎾 🎾\n支付方式零钱\n交易状态支付成功，对方已收款","height":0,"width":0,"cover":""}},"template_detail":{"text_content":{"text":"","cover":"","color":""},"new_tmpl_type":0,"opitems":{"opitem":{"op_type":0,"icon":"","word":"查看账单详情","is_rich_text":0,"display_line_number":0,"weapp_version":0,"color":"#000000","hint_word":"","weapp_username":"","url":"https://wx.tenpay.com/cgi-bin/mmpayweb-bin/jumpuserroll?trans_id=100010710120052500064222108623480836&accid=085e9858e34ce9d3aa34b03bd","weapp_path":"","weapp_state":0},"show_type":1},"line_content":{"topline":{"key":{"hide_dash_line":1,"color":"#888888","word":"付款金额"},"value":{"small_text_count":1,"color":"#000000","word":"￥0.01"}},"lines":{"line":[{"key":{"color":"#888888","word":"收款方"},"value":{"color":"#000000","word":"🎾 🎾 🎾 🎾 🎾 🎾"}},{"key":{"color":"#888888","word":"支付方式"},"value":{"color":"#000000","word":"零钱"}},{"key":{"color":"#888888","word":"交易状态"},"value":{"color":"#000000","word":"支付成功，对方已收款"}}]}},"template_show_type":1},"template_header":{"pay_style":1,"pub_time":1590398008,"shortcut_icon_url":"","show_icon_and_display_name":0,"header_jump_url":"","hide_icon_and_display_name_line":1,"icon_url":"","ignore_hide_title_and_time":1,"display_name":"","hide_title_and_time":1,"hide_time":1,"first_color":"","title_color":"","title":"微信支付凭证","first_data":""},"publisher":{"username":"wxzhifu","nickname":"微信支付"}},"content":"","template_id":"ey45ZWkUmYUBk_fMgxBLvyaFqVop1rmoWLFd62OXGiU","extinfo":"","lowurl":"","soundtype":0,"showtype":1,"title":"微信支付凭证","sourceusername":"","sourcedisplayname":"","sdkver":0,"des":"付款金额￥0.01\n收款方🎾 🎾 🎾 🎾 🎾 🎾\n支付方式零钱\n交易状态支付成功，对方已收款","contentattr":0,"type":5}}
     */

    private MsgBean msg;

    public MsgBean getMsg() {
        return msg;
    }

    public void setMsg(MsgBean msg) {
        this.msg = msg;
    }

    public static class MsgBean {
        /**
         * appinfo : {"isforceupdate":1,"appname":"微信支付","version":0}
         * fromusername : gh_3dfda90e39d6
         * appmsg : {"appattach":{"totallen":0,"fileext":"","cdnthumbaeskey":"","aeskey":"","cdnthumburl":"","attachid":""},"action":"","url":"https://wx.tenpay.com/cgi-bin/mmpayweb-bin/jumpuserroll?trans_id=100010710120052500064222108623480836&accid=085e9858e34ce9d3aa34b03bd","thumburl":"","appid":"","mmreader":{"forbid_forward":0,"category":{"name":"微信支付","count":1,"item":{"recommendation":"","del_flag":0,"weapp_username":"","url":"https://wx.tenpay.com/cgi-bin/mmpayweb-bin/jumpuserroll?trans_id=100010710120052500064222108623480836&accid=085e9858e34ce9d3aa34b03bd","weapp_state":0,"cover_1_1":"","is_pay_subscribe":0,"music_source":0,"weapp_path":"","pic_urls":"","digest":"付款金额￥0.01\n收款方🎾 🎾 🎾 🎾 🎾 🎾\n支付方式零钱\n交易状态支付成功，对方已收款","vid":"","tweetid":"","pub_time":1590398008,"comment_topic_id":0,"video_height":0,"shorturl":"","sources":{"source":{"name":"微信支付"}},"itemshowtype":4,"cover":"","native_url":"","play_url":"","title":"微信支付凭证","weapp_version":0,"longurl":"","pic_num":0,"appmsg_like_type":0,"template_op_type":0,"contentattr":0,"cover_235_1":"","player":"","video_width":0,"fileid":0,"show_complaint_button":0,"play_length":0,"styles":{"style":[{"range":"{4,5}","color":"#000000","font":"s"},{"range":"{13,11}","color":"#000000","font":"s"},{"range":"{29,2}","color":"#000000","font":"s"},{"range":"{36,10}","color":"#000000","font":"s"}],"topColor":""}},"type":0,"topnew":{"digest":"付款金额￥0.01\n收款方🎾 🎾 🎾 🎾 🎾 🎾\n支付方式零钱\n交易状态支付成功，对方已收款","height":0,"width":0,"cover":""}},"template_detail":{"text_content":{"text":"","cover":"","color":""},"new_tmpl_type":0,"opitems":{"opitem":{"op_type":0,"icon":"","word":"查看账单详情","is_rich_text":0,"display_line_number":0,"weapp_version":0,"color":"#000000","hint_word":"","weapp_username":"","url":"https://wx.tenpay.com/cgi-bin/mmpayweb-bin/jumpuserroll?trans_id=100010710120052500064222108623480836&accid=085e9858e34ce9d3aa34b03bd","weapp_path":"","weapp_state":0},"show_type":1},"line_content":{"topline":{"key":{"hide_dash_line":1,"color":"#888888","word":"付款金额"},"value":{"small_text_count":1,"color":"#000000","word":"￥0.01"}},"lines":{"line":[{"key":{"color":"#888888","word":"收款方"},"value":{"color":"#000000","word":"🎾 🎾 🎾 🎾 🎾 🎾"}},{"key":{"color":"#888888","word":"支付方式"},"value":{"color":"#000000","word":"零钱"}},{"key":{"color":"#888888","word":"交易状态"},"value":{"color":"#000000","word":"支付成功，对方已收款"}}]}},"template_show_type":1},"template_header":{"pay_style":1,"pub_time":1590398008,"shortcut_icon_url":"","show_icon_and_display_name":0,"header_jump_url":"","hide_icon_and_display_name_line":1,"icon_url":"","ignore_hide_title_and_time":1,"display_name":"","hide_title_and_time":1,"hide_time":1,"first_color":"","title_color":"","title":"微信支付凭证","first_data":""},"publisher":{"username":"wxzhifu","nickname":"微信支付"}},"content":"","template_id":"ey45ZWkUmYUBk_fMgxBLvyaFqVop1rmoWLFd62OXGiU","extinfo":"","lowurl":"","soundtype":0,"showtype":1,"title":"微信支付凭证","sourceusername":"","sourcedisplayname":"","sdkver":0,"des":"付款金额￥0.01\n收款方🎾 🎾 🎾 🎾 🎾 🎾\n支付方式零钱\n交易状态支付成功，对方已收款","contentattr":0,"type":5}
         */

        private AppinfoBean appinfo;
        private String fromusername;
        private AppmsgBean appmsg;

        public AppinfoBean getAppinfo() {
            return appinfo;
        }

        public void setAppinfo(AppinfoBean appinfo) {
            this.appinfo = appinfo;
        }

        public String getFromusername() {
            return fromusername;
        }

        public void setFromusername(String fromusername) {
            this.fromusername = fromusername;
        }

        public AppmsgBean getAppmsg() {
            return appmsg;
        }

        public void setAppmsg(AppmsgBean appmsg) {
            this.appmsg = appmsg;
        }

        public static class AppinfoBean {
            /**
             * isforceupdate : 1
             * appname : 微信支付
             * version : 0
             */

            private int isforceupdate;
            private String appname;
            private int version;

            public int getIsforceupdate() {
                return isforceupdate;
            }

            public void setIsforceupdate(int isforceupdate) {
                this.isforceupdate = isforceupdate;
            }

            public String getAppname() {
                return appname;
            }

            public void setAppname(String appname) {
                this.appname = appname;
            }

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
            }
        }

        public static class AppmsgBean {
            /**
             * appattach : {"totallen":0,"fileext":"","cdnthumbaeskey":"","aeskey":"","cdnthumburl":"","attachid":""}
             * action :
             * url : https://wx.tenpay.com/cgi-bin/mmpayweb-bin/jumpuserroll?trans_id=100010710120052500064222108623480836&accid=085e9858e34ce9d3aa34b03bd
             * thumburl :
             * appid :
             * mmreader : {"forbid_forward":0,"category":{"name":"微信支付","count":1,"item":{"recommendation":"","del_flag":0,"weapp_username":"","url":"https://wx.tenpay.com/cgi-bin/mmpayweb-bin/jumpuserroll?trans_id=100010710120052500064222108623480836&accid=085e9858e34ce9d3aa34b03bd","weapp_state":0,"cover_1_1":"","is_pay_subscribe":0,"music_source":0,"weapp_path":"","pic_urls":"","digest":"付款金额￥0.01\n收款方🎾 🎾 🎾 🎾 🎾 🎾\n支付方式零钱\n交易状态支付成功，对方已收款","vid":"","tweetid":"","pub_time":1590398008,"comment_topic_id":0,"video_height":0,"shorturl":"","sources":{"source":{"name":"微信支付"}},"itemshowtype":4,"cover":"","native_url":"","play_url":"","title":"微信支付凭证","weapp_version":0,"longurl":"","pic_num":0,"appmsg_like_type":0,"template_op_type":0,"contentattr":0,"cover_235_1":"","player":"","video_width":0,"fileid":0,"show_complaint_button":0,"play_length":0,"styles":{"style":[{"range":"{4,5}","color":"#000000","font":"s"},{"range":"{13,11}","color":"#000000","font":"s"},{"range":"{29,2}","color":"#000000","font":"s"},{"range":"{36,10}","color":"#000000","font":"s"}],"topColor":""}},"type":0,"topnew":{"digest":"付款金额￥0.01\n收款方🎾 🎾 🎾 🎾 🎾 🎾\n支付方式零钱\n交易状态支付成功，对方已收款","height":0,"width":0,"cover":""}},"template_detail":{"text_content":{"text":"","cover":"","color":""},"new_tmpl_type":0,"opitems":{"opitem":{"op_type":0,"icon":"","word":"查看账单详情","is_rich_text":0,"display_line_number":0,"weapp_version":0,"color":"#000000","hint_word":"","weapp_username":"","url":"https://wx.tenpay.com/cgi-bin/mmpayweb-bin/jumpuserroll?trans_id=100010710120052500064222108623480836&accid=085e9858e34ce9d3aa34b03bd","weapp_path":"","weapp_state":0},"show_type":1},"line_content":{"topline":{"key":{"hide_dash_line":1,"color":"#888888","word":"付款金额"},"value":{"small_text_count":1,"color":"#000000","word":"￥0.01"}},"lines":{"line":[{"key":{"color":"#888888","word":"收款方"},"value":{"color":"#000000","word":"🎾 🎾 🎾 🎾 🎾 🎾"}},{"key":{"color":"#888888","word":"支付方式"},"value":{"color":"#000000","word":"零钱"}},{"key":{"color":"#888888","word":"交易状态"},"value":{"color":"#000000","word":"支付成功，对方已收款"}}]}},"template_show_type":1},"template_header":{"pay_style":1,"pub_time":1590398008,"shortcut_icon_url":"","show_icon_and_display_name":0,"header_jump_url":"","hide_icon_and_display_name_line":1,"icon_url":"","ignore_hide_title_and_time":1,"display_name":"","hide_title_and_time":1,"hide_time":1,"first_color":"","title_color":"","title":"微信支付凭证","first_data":""},"publisher":{"username":"wxzhifu","nickname":"微信支付"}}
             * content :
             * template_id : ey45ZWkUmYUBk_fMgxBLvyaFqVop1rmoWLFd62OXGiU
             * extinfo :
             * lowurl :
             * soundtype : 0
             * showtype : 1
             * title : 微信支付凭证
             * sourceusername :
             * sourcedisplayname :
             * sdkver : 0
             * des : 付款金额￥0.01
             收款方🎾 🎾 🎾 🎾 🎾 🎾
             支付方式零钱
             交易状态支付成功，对方已收款
             * contentattr : 0
             * type : 5
             */

            private AppattachBean appattach;
            private String action;
            private String url;
            private String thumburl;
            private String appid;
            private MmreaderBean mmreader;
            private String content;
            private String template_id;
            private String extinfo;
            private String lowurl;
            private int soundtype;
            private int showtype;
            private String title;
            private String sourceusername;
            private String sourcedisplayname;
            private int sdkver;
            private String des;
            private int contentattr;
            private int type;

            public AppattachBean getAppattach() {
                return appattach;
            }

            public void setAppattach(AppattachBean appattach) {
                this.appattach = appattach;
            }

            public String getAction() {
                return action;
            }

            public void setAction(String action) {
                this.action = action;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getThumburl() {
                return thumburl;
            }

            public void setThumburl(String thumburl) {
                this.thumburl = thumburl;
            }

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public MmreaderBean getMmreader() {
                return mmreader;
            }

            public void setMmreader(MmreaderBean mmreader) {
                this.mmreader = mmreader;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getTemplate_id() {
                return template_id;
            }

            public void setTemplate_id(String template_id) {
                this.template_id = template_id;
            }

            public String getExtinfo() {
                return extinfo;
            }

            public void setExtinfo(String extinfo) {
                this.extinfo = extinfo;
            }

            public String getLowurl() {
                return lowurl;
            }

            public void setLowurl(String lowurl) {
                this.lowurl = lowurl;
            }

            public int getSoundtype() {
                return soundtype;
            }

            public void setSoundtype(int soundtype) {
                this.soundtype = soundtype;
            }

            public int getShowtype() {
                return showtype;
            }

            public void setShowtype(int showtype) {
                this.showtype = showtype;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSourceusername() {
                return sourceusername;
            }

            public void setSourceusername(String sourceusername) {
                this.sourceusername = sourceusername;
            }

            public String getSourcedisplayname() {
                return sourcedisplayname;
            }

            public void setSourcedisplayname(String sourcedisplayname) {
                this.sourcedisplayname = sourcedisplayname;
            }

            public int getSdkver() {
                return sdkver;
            }

            public void setSdkver(int sdkver) {
                this.sdkver = sdkver;
            }

            public String getDes() {
                return des;
            }

            public void setDes(String des) {
                this.des = des;
            }

            public int getContentattr() {
                return contentattr;
            }

            public void setContentattr(int contentattr) {
                this.contentattr = contentattr;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public static class AppattachBean {
                /**
                 * totallen : 0
                 * fileext :
                 * cdnthumbaeskey :
                 * aeskey :
                 * cdnthumburl :
                 * attachid :
                 */

                private int totallen;
                private String fileext;
                private String cdnthumbaeskey;
                private String aeskey;
                private String cdnthumburl;
                private String attachid;

                public int getTotallen() {
                    return totallen;
                }

                public void setTotallen(int totallen) {
                    this.totallen = totallen;
                }

                public String getFileext() {
                    return fileext;
                }

                public void setFileext(String fileext) {
                    this.fileext = fileext;
                }

                public String getCdnthumbaeskey() {
                    return cdnthumbaeskey;
                }

                public void setCdnthumbaeskey(String cdnthumbaeskey) {
                    this.cdnthumbaeskey = cdnthumbaeskey;
                }

                public String getAeskey() {
                    return aeskey;
                }

                public void setAeskey(String aeskey) {
                    this.aeskey = aeskey;
                }

                public String getCdnthumburl() {
                    return cdnthumburl;
                }

                public void setCdnthumburl(String cdnthumburl) {
                    this.cdnthumburl = cdnthumburl;
                }

                public String getAttachid() {
                    return attachid;
                }

                public void setAttachid(String attachid) {
                    this.attachid = attachid;
                }
            }

            public static class MmreaderBean {
                /**
                 * forbid_forward : 0
                 * category : {"name":"微信支付","count":1,"item":{"recommendation":"","del_flag":0,"weapp_username":"","url":"https://wx.tenpay.com/cgi-bin/mmpayweb-bin/jumpuserroll?trans_id=100010710120052500064222108623480836&accid=085e9858e34ce9d3aa34b03bd","weapp_state":0,"cover_1_1":"","is_pay_subscribe":0,"music_source":0,"weapp_path":"","pic_urls":"","digest":"付款金额￥0.01\n收款方🎾 🎾 🎾 🎾 🎾 🎾\n支付方式零钱\n交易状态支付成功，对方已收款","vid":"","tweetid":"","pub_time":1590398008,"comment_topic_id":0,"video_height":0,"shorturl":"","sources":{"source":{"name":"微信支付"}},"itemshowtype":4,"cover":"","native_url":"","play_url":"","title":"微信支付凭证","weapp_version":0,"longurl":"","pic_num":0,"appmsg_like_type":0,"template_op_type":0,"contentattr":0,"cover_235_1":"","player":"","video_width":0,"fileid":0,"show_complaint_button":0,"play_length":0,"styles":{"style":[{"range":"{4,5}","color":"#000000","font":"s"},{"range":"{13,11}","color":"#000000","font":"s"},{"range":"{29,2}","color":"#000000","font":"s"},{"range":"{36,10}","color":"#000000","font":"s"}],"topColor":""}},"type":0,"topnew":{"digest":"付款金额￥0.01\n收款方🎾 🎾 🎾 🎾 🎾 🎾\n支付方式零钱\n交易状态支付成功，对方已收款","height":0,"width":0,"cover":""}}
                 * template_detail : {"text_content":{"text":"","cover":"","color":""},"new_tmpl_type":0,"opitems":{"opitem":{"op_type":0,"icon":"","word":"查看账单详情","is_rich_text":0,"display_line_number":0,"weapp_version":0,"color":"#000000","hint_word":"","weapp_username":"","url":"https://wx.tenpay.com/cgi-bin/mmpayweb-bin/jumpuserroll?trans_id=100010710120052500064222108623480836&accid=085e9858e34ce9d3aa34b03bd","weapp_path":"","weapp_state":0},"show_type":1},"line_content":{"topline":{"key":{"hide_dash_line":1,"color":"#888888","word":"付款金额"},"value":{"small_text_count":1,"color":"#000000","word":"￥0.01"}},"lines":{"line":[{"key":{"color":"#888888","word":"收款方"},"value":{"color":"#000000","word":"🎾 🎾 🎾 🎾 🎾 🎾"}},{"key":{"color":"#888888","word":"支付方式"},"value":{"color":"#000000","word":"零钱"}},{"key":{"color":"#888888","word":"交易状态"},"value":{"color":"#000000","word":"支付成功，对方已收款"}}]}},"template_show_type":1}
                 * template_header : {"pay_style":1,"pub_time":1590398008,"shortcut_icon_url":"","show_icon_and_display_name":0,"header_jump_url":"","hide_icon_and_display_name_line":1,"icon_url":"","ignore_hide_title_and_time":1,"display_name":"","hide_title_and_time":1,"hide_time":1,"first_color":"","title_color":"","title":"微信支付凭证","first_data":""}
                 * publisher : {"username":"wxzhifu","nickname":"微信支付"}
                 */

                private int forbid_forward;
                private CategoryBean category;
                private TemplateDetailBean template_detail;
                private TemplateHeaderBean template_header;
                private PublisherBean publisher;

                public int getForbid_forward() {
                    return forbid_forward;
                }

                public void setForbid_forward(int forbid_forward) {
                    this.forbid_forward = forbid_forward;
                }

                public CategoryBean getCategory() {
                    return category;
                }

                public void setCategory(CategoryBean category) {
                    this.category = category;
                }

                public TemplateDetailBean getTemplate_detail() {
                    return template_detail;
                }

                public void setTemplate_detail(TemplateDetailBean template_detail) {
                    this.template_detail = template_detail;
                }

                public TemplateHeaderBean getTemplate_header() {
                    return template_header;
                }

                public void setTemplate_header(TemplateHeaderBean template_header) {
                    this.template_header = template_header;
                }

                public PublisherBean getPublisher() {
                    return publisher;
                }

                public void setPublisher(PublisherBean publisher) {
                    this.publisher = publisher;
                }

                public static class CategoryBean {
                    /**
                     * name : 微信支付
                     * count : 1
                     * item : {"recommendation":"","del_flag":0,"weapp_username":"","url":"https://wx.tenpay.com/cgi-bin/mmpayweb-bin/jumpuserroll?trans_id=100010710120052500064222108623480836&accid=085e9858e34ce9d3aa34b03bd","weapp_state":0,"cover_1_1":"","is_pay_subscribe":0,"music_source":0,"weapp_path":"","pic_urls":"","digest":"付款金额￥0.01\n收款方🎾 🎾 🎾 🎾 🎾 🎾\n支付方式零钱\n交易状态支付成功，对方已收款","vid":"","tweetid":"","pub_time":1590398008,"comment_topic_id":0,"video_height":0,"shorturl":"","sources":{"source":{"name":"微信支付"}},"itemshowtype":4,"cover":"","native_url":"","play_url":"","title":"微信支付凭证","weapp_version":0,"longurl":"","pic_num":0,"appmsg_like_type":0,"template_op_type":0,"contentattr":0,"cover_235_1":"","player":"","video_width":0,"fileid":0,"show_complaint_button":0,"play_length":0,"styles":{"style":[{"range":"{4,5}","color":"#000000","font":"s"},{"range":"{13,11}","color":"#000000","font":"s"},{"range":"{29,2}","color":"#000000","font":"s"},{"range":"{36,10}","color":"#000000","font":"s"}],"topColor":""}}
                     * type : 0
                     * topnew : {"digest":"付款金额￥0.01\n收款方🎾 🎾 🎾 🎾 🎾 🎾\n支付方式零钱\n交易状态支付成功，对方已收款","height":0,"width":0,"cover":""}
                     */

                    private String name;
                    private int count;
                    private ItemBean item;
                    private int type;
                    private TopnewBean topnew;

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public int getCount() {
                        return count;
                    }

                    public void setCount(int count) {
                        this.count = count;
                    }

                    public ItemBean getItem() {
                        return item;
                    }

                    public void setItem(ItemBean item) {
                        this.item = item;
                    }

                    public int getType() {
                        return type;
                    }

                    public void setType(int type) {
                        this.type = type;
                    }

                    public TopnewBean getTopnew() {
                        return topnew;
                    }

                    public void setTopnew(TopnewBean topnew) {
                        this.topnew = topnew;
                    }

                    public static class ItemBean {
                        /**
                         * recommendation :
                         * del_flag : 0
                         * weapp_username :
                         * url : https://wx.tenpay.com/cgi-bin/mmpayweb-bin/jumpuserroll?trans_id=100010710120052500064222108623480836&accid=085e9858e34ce9d3aa34b03bd
                         * weapp_state : 0
                         * cover_1_1 :
                         * is_pay_subscribe : 0
                         * music_source : 0
                         * weapp_path :
                         * pic_urls :
                         * digest : 付款金额￥0.01
                         收款方🎾 🎾 🎾 🎾 🎾 🎾
                         支付方式零钱
                         交易状态支付成功，对方已收款
                         * vid :
                         * tweetid :
                         * pub_time : 1590398008
                         * comment_topic_id : 0
                         * video_height : 0
                         * shorturl :
                         * sources : {"source":{"name":"微信支付"}}
                         * itemshowtype : 4
                         * cover :
                         * native_url :
                         * play_url :
                         * title : 微信支付凭证
                         * weapp_version : 0
                         * longurl :
                         * pic_num : 0
                         * appmsg_like_type : 0
                         * template_op_type : 0
                         * contentattr : 0
                         * cover_235_1 :
                         * player :
                         * video_width : 0
                         * fileid : 0
                         * show_complaint_button : 0
                         * play_length : 0
                         * styles : {"style":[{"range":"{4,5}","color":"#000000","font":"s"},{"range":"{13,11}","color":"#000000","font":"s"},{"range":"{29,2}","color":"#000000","font":"s"},{"range":"{36,10}","color":"#000000","font":"s"}],"topColor":""}
                         */

                        private String recommendation;
                        private int del_flag;
                        private String weapp_username;
                        private String url;
                        private int weapp_state;
                        private String cover_1_1;
                        private int is_pay_subscribe;
                        private int music_source;
                        private String weapp_path;
                        private String pic_urls;
                        private String digest;
                        private String vid;
                        private String tweetid;
                        private int pub_time;
                        private int comment_topic_id;
                        private int video_height;
                        private String shorturl;
                        private SourcesBean sources;
                        private int itemshowtype;
                        private String cover;
                        private String native_url;
                        private String play_url;
                        private String title;
                        private int weapp_version;
                        private String longurl;
                        private int pic_num;
                        private int appmsg_like_type;
                        private int template_op_type;
                        private int contentattr;
                        private String cover_235_1;
                        private String player;
                        private int video_width;
                        private int fileid;
                        private int show_complaint_button;
                        private int play_length;
                        private StylesBean styles;

                        public String getRecommendation() {
                            return recommendation;
                        }

                        public void setRecommendation(String recommendation) {
                            this.recommendation = recommendation;
                        }

                        public int getDel_flag() {
                            return del_flag;
                        }

                        public void setDel_flag(int del_flag) {
                            this.del_flag = del_flag;
                        }

                        public String getWeapp_username() {
                            return weapp_username;
                        }

                        public void setWeapp_username(String weapp_username) {
                            this.weapp_username = weapp_username;
                        }

                        public String getUrl() {
                            return url;
                        }

                        public void setUrl(String url) {
                            this.url = url;
                        }

                        public int getWeapp_state() {
                            return weapp_state;
                        }

                        public void setWeapp_state(int weapp_state) {
                            this.weapp_state = weapp_state;
                        }

                        public String getCover_1_1() {
                            return cover_1_1;
                        }

                        public void setCover_1_1(String cover_1_1) {
                            this.cover_1_1 = cover_1_1;
                        }

                        public int getIs_pay_subscribe() {
                            return is_pay_subscribe;
                        }

                        public void setIs_pay_subscribe(int is_pay_subscribe) {
                            this.is_pay_subscribe = is_pay_subscribe;
                        }

                        public int getMusic_source() {
                            return music_source;
                        }

                        public void setMusic_source(int music_source) {
                            this.music_source = music_source;
                        }

                        public String getWeapp_path() {
                            return weapp_path;
                        }

                        public void setWeapp_path(String weapp_path) {
                            this.weapp_path = weapp_path;
                        }

                        public String getPic_urls() {
                            return pic_urls;
                        }

                        public void setPic_urls(String pic_urls) {
                            this.pic_urls = pic_urls;
                        }

                        public String getDigest() {
                            return digest;
                        }

                        public void setDigest(String digest) {
                            this.digest = digest;
                        }

                        public String getVid() {
                            return vid;
                        }

                        public void setVid(String vid) {
                            this.vid = vid;
                        }

                        public String getTweetid() {
                            return tweetid;
                        }

                        public void setTweetid(String tweetid) {
                            this.tweetid = tweetid;
                        }

                        public int getPub_time() {
                            return pub_time;
                        }

                        public void setPub_time(int pub_time) {
                            this.pub_time = pub_time;
                        }

                        public int getComment_topic_id() {
                            return comment_topic_id;
                        }

                        public void setComment_topic_id(int comment_topic_id) {
                            this.comment_topic_id = comment_topic_id;
                        }

                        public int getVideo_height() {
                            return video_height;
                        }

                        public void setVideo_height(int video_height) {
                            this.video_height = video_height;
                        }

                        public String getShorturl() {
                            return shorturl;
                        }

                        public void setShorturl(String shorturl) {
                            this.shorturl = shorturl;
                        }

                        public SourcesBean getSources() {
                            return sources;
                        }

                        public void setSources(SourcesBean sources) {
                            this.sources = sources;
                        }

                        public int getItemshowtype() {
                            return itemshowtype;
                        }

                        public void setItemshowtype(int itemshowtype) {
                            this.itemshowtype = itemshowtype;
                        }

                        public String getCover() {
                            return cover;
                        }

                        public void setCover(String cover) {
                            this.cover = cover;
                        }

                        public String getNative_url() {
                            return native_url;
                        }

                        public void setNative_url(String native_url) {
                            this.native_url = native_url;
                        }

                        public String getPlay_url() {
                            return play_url;
                        }

                        public void setPlay_url(String play_url) {
                            this.play_url = play_url;
                        }

                        public String getTitle() {
                            return title;
                        }

                        public void setTitle(String title) {
                            this.title = title;
                        }

                        public int getWeapp_version() {
                            return weapp_version;
                        }

                        public void setWeapp_version(int weapp_version) {
                            this.weapp_version = weapp_version;
                        }

                        public String getLongurl() {
                            return longurl;
                        }

                        public void setLongurl(String longurl) {
                            this.longurl = longurl;
                        }

                        public int getPic_num() {
                            return pic_num;
                        }

                        public void setPic_num(int pic_num) {
                            this.pic_num = pic_num;
                        }

                        public int getAppmsg_like_type() {
                            return appmsg_like_type;
                        }

                        public void setAppmsg_like_type(int appmsg_like_type) {
                            this.appmsg_like_type = appmsg_like_type;
                        }

                        public int getTemplate_op_type() {
                            return template_op_type;
                        }

                        public void setTemplate_op_type(int template_op_type) {
                            this.template_op_type = template_op_type;
                        }

                        public int getContentattr() {
                            return contentattr;
                        }

                        public void setContentattr(int contentattr) {
                            this.contentattr = contentattr;
                        }

                        public String getCover_235_1() {
                            return cover_235_1;
                        }

                        public void setCover_235_1(String cover_235_1) {
                            this.cover_235_1 = cover_235_1;
                        }

                        public String getPlayer() {
                            return player;
                        }

                        public void setPlayer(String player) {
                            this.player = player;
                        }

                        public int getVideo_width() {
                            return video_width;
                        }

                        public void setVideo_width(int video_width) {
                            this.video_width = video_width;
                        }

                        public int getFileid() {
                            return fileid;
                        }

                        public void setFileid(int fileid) {
                            this.fileid = fileid;
                        }

                        public int getShow_complaint_button() {
                            return show_complaint_button;
                        }

                        public void setShow_complaint_button(int show_complaint_button) {
                            this.show_complaint_button = show_complaint_button;
                        }

                        public int getPlay_length() {
                            return play_length;
                        }

                        public void setPlay_length(int play_length) {
                            this.play_length = play_length;
                        }

                        public StylesBean getStyles() {
                            return styles;
                        }

                        public void setStyles(StylesBean styles) {
                            this.styles = styles;
                        }

                        public static class SourcesBean {
                            /**
                             * source : {"name":"微信支付"}
                             */

                            private SourceBean source;

                            public SourceBean getSource() {
                                return source;
                            }

                            public void setSource(SourceBean source) {
                                this.source = source;
                            }

                            public static class SourceBean {
                                /**
                                 * name : 微信支付
                                 */

                                private String name;

                                public String getName() {
                                    return name;
                                }

                                public void setName(String name) {
                                    this.name = name;
                                }
                            }
                        }

                        public static class StylesBean {
                            /**
                             * style : [{"range":"{4,5}","color":"#000000","font":"s"},{"range":"{13,11}","color":"#000000","font":"s"},{"range":"{29,2}","color":"#000000","font":"s"},{"range":"{36,10}","color":"#000000","font":"s"}]
                             * topColor :
                             */

                            private String topColor;
                            private List<StyleBean> style;

                            public String getTopColor() {
                                return topColor;
                            }

                            public void setTopColor(String topColor) {
                                this.topColor = topColor;
                            }

                            public List<StyleBean> getStyle() {
                                return style;
                            }

                            public void setStyle(List<StyleBean> style) {
                                this.style = style;
                            }

                            public static class StyleBean {
                                /**
                                 * range : {4,5}
                                 * color : #000000
                                 * font : s
                                 */

                                private String range;
                                private String color;
                                private String font;

                                public String getRange() {
                                    return range;
                                }

                                public void setRange(String range) {
                                    this.range = range;
                                }

                                public String getColor() {
                                    return color;
                                }

                                public void setColor(String color) {
                                    this.color = color;
                                }

                                public String getFont() {
                                    return font;
                                }

                                public void setFont(String font) {
                                    this.font = font;
                                }
                            }
                        }
                    }

                    public static class TopnewBean {
                        /**
                         * digest : 付款金额￥0.01
                         收款方🎾 🎾 🎾 🎾 🎾 🎾
                         支付方式零钱
                         交易状态支付成功，对方已收款
                         * height : 0
                         * width : 0
                         * cover :
                         */

                        private String digest;
                        private int height;
                        private int width;
                        private String cover;

                        public String getDigest() {
                            return digest;
                        }

                        public void setDigest(String digest) {
                            this.digest = digest;
                        }

                        public int getHeight() {
                            return height;
                        }

                        public void setHeight(int height) {
                            this.height = height;
                        }

                        public int getWidth() {
                            return width;
                        }

                        public void setWidth(int width) {
                            this.width = width;
                        }

                        public String getCover() {
                            return cover;
                        }

                        public void setCover(String cover) {
                            this.cover = cover;
                        }
                    }
                }

                public static class TemplateDetailBean {
                    /**
                     * text_content : {"text":"","cover":"","color":""}
                     * new_tmpl_type : 0
                     * opitems : {"opitem":{"op_type":0,"icon":"","word":"查看账单详情","is_rich_text":0,"display_line_number":0,"weapp_version":0,"color":"#000000","hint_word":"","weapp_username":"","url":"https://wx.tenpay.com/cgi-bin/mmpayweb-bin/jumpuserroll?trans_id=100010710120052500064222108623480836&accid=085e9858e34ce9d3aa34b03bd","weapp_path":"","weapp_state":0},"show_type":1}
                     * line_content : {"topline":{"key":{"hide_dash_line":1,"color":"#888888","word":"付款金额"},"value":{"small_text_count":1,"color":"#000000","word":"￥0.01"}},"lines":{"line":[{"key":{"color":"#888888","word":"收款方"},"value":{"color":"#000000","word":"🎾 🎾 🎾 🎾 🎾 🎾"}},{"key":{"color":"#888888","word":"支付方式"},"value":{"color":"#000000","word":"零钱"}},{"key":{"color":"#888888","word":"交易状态"},"value":{"color":"#000000","word":"支付成功，对方已收款"}}]}}
                     * template_show_type : 1
                     */

                    private TextContentBean text_content;
                    private int new_tmpl_type;
                    private OpitemsBean opitems;
                    private LineContentBean line_content;
                    private int template_show_type;

                    public TextContentBean getText_content() {
                        return text_content;
                    }

                    public void setText_content(TextContentBean text_content) {
                        this.text_content = text_content;
                    }

                    public int getNew_tmpl_type() {
                        return new_tmpl_type;
                    }

                    public void setNew_tmpl_type(int new_tmpl_type) {
                        this.new_tmpl_type = new_tmpl_type;
                    }

                    public OpitemsBean getOpitems() {
                        return opitems;
                    }

                    public void setOpitems(OpitemsBean opitems) {
                        this.opitems = opitems;
                    }

                    public LineContentBean getLine_content() {
                        return line_content;
                    }

                    public void setLine_content(LineContentBean line_content) {
                        this.line_content = line_content;
                    }

                    public int getTemplate_show_type() {
                        return template_show_type;
                    }

                    public void setTemplate_show_type(int template_show_type) {
                        this.template_show_type = template_show_type;
                    }

                    public static class TextContentBean {
                        /**
                         * text :
                         * cover :
                         * color :
                         */

                        private String text;
                        private String cover;
                        private String color;

                        public String getText() {
                            return text;
                        }

                        public void setText(String text) {
                            this.text = text;
                        }

                        public String getCover() {
                            return cover;
                        }

                        public void setCover(String cover) {
                            this.cover = cover;
                        }

                        public String getColor() {
                            return color;
                        }

                        public void setColor(String color) {
                            this.color = color;
                        }
                    }

                    public static class OpitemsBean {
                        /**
                         * opitem : {"op_type":0,"icon":"","word":"查看账单详情","is_rich_text":0,"display_line_number":0,"weapp_version":0,"color":"#000000","hint_word":"","weapp_username":"","url":"https://wx.tenpay.com/cgi-bin/mmpayweb-bin/jumpuserroll?trans_id=100010710120052500064222108623480836&accid=085e9858e34ce9d3aa34b03bd","weapp_path":"","weapp_state":0}
                         * show_type : 1
                         */

                        private OpitemBean opitem;
                        private int show_type;

                        public OpitemBean getOpitem() {
                            return opitem;
                        }

                        public void setOpitem(OpitemBean opitem) {
                            this.opitem = opitem;
                        }

                        public int getShow_type() {
                            return show_type;
                        }

                        public void setShow_type(int show_type) {
                            this.show_type = show_type;
                        }

                        public static class OpitemBean {
                            /**
                             * op_type : 0
                             * icon :
                             * word : 查看账单详情
                             * is_rich_text : 0
                             * display_line_number : 0
                             * weapp_version : 0
                             * color : #000000
                             * hint_word :
                             * weapp_username :
                             * url : https://wx.tenpay.com/cgi-bin/mmpayweb-bin/jumpuserroll?trans_id=100010710120052500064222108623480836&accid=085e9858e34ce9d3aa34b03bd
                             * weapp_path :
                             * weapp_state : 0
                             */

                            private int op_type;
                            private String icon;
                            private String word;
                            private int is_rich_text;
                            private int display_line_number;
                            private int weapp_version;
                            private String color;
                            private String hint_word;
                            private String weapp_username;
                            private String url;
                            private String weapp_path;
                            private int weapp_state;

                            public int getOp_type() {
                                return op_type;
                            }

                            public void setOp_type(int op_type) {
                                this.op_type = op_type;
                            }

                            public String getIcon() {
                                return icon;
                            }

                            public void setIcon(String icon) {
                                this.icon = icon;
                            }

                            public String getWord() {
                                return word;
                            }

                            public void setWord(String word) {
                                this.word = word;
                            }

                            public int getIs_rich_text() {
                                return is_rich_text;
                            }

                            public void setIs_rich_text(int is_rich_text) {
                                this.is_rich_text = is_rich_text;
                            }

                            public int getDisplay_line_number() {
                                return display_line_number;
                            }

                            public void setDisplay_line_number(int display_line_number) {
                                this.display_line_number = display_line_number;
                            }

                            public int getWeapp_version() {
                                return weapp_version;
                            }

                            public void setWeapp_version(int weapp_version) {
                                this.weapp_version = weapp_version;
                            }

                            public String getColor() {
                                return color;
                            }

                            public void setColor(String color) {
                                this.color = color;
                            }

                            public String getHint_word() {
                                return hint_word;
                            }

                            public void setHint_word(String hint_word) {
                                this.hint_word = hint_word;
                            }

                            public String getWeapp_username() {
                                return weapp_username;
                            }

                            public void setWeapp_username(String weapp_username) {
                                this.weapp_username = weapp_username;
                            }

                            public String getUrl() {
                                return url;
                            }

                            public void setUrl(String url) {
                                this.url = url;
                            }

                            public String getWeapp_path() {
                                return weapp_path;
                            }

                            public void setWeapp_path(String weapp_path) {
                                this.weapp_path = weapp_path;
                            }

                            public int getWeapp_state() {
                                return weapp_state;
                            }

                            public void setWeapp_state(int weapp_state) {
                                this.weapp_state = weapp_state;
                            }
                        }
                    }

                    public static class LineContentBean {
                        /**
                         * topline : {"key":{"hide_dash_line":1,"color":"#888888","word":"付款金额"},"value":{"small_text_count":1,"color":"#000000","word":"￥0.01"}}
                         * lines : {"line":[{"key":{"color":"#888888","word":"收款方"},"value":{"color":"#000000","word":"🎾 🎾 🎾 🎾 🎾 🎾"}},{"key":{"color":"#888888","word":"支付方式"},"value":{"color":"#000000","word":"零钱"}},{"key":{"color":"#888888","word":"交易状态"},"value":{"color":"#000000","word":"支付成功，对方已收款"}}]}
                         */

                        private ToplineBean topline;
                        private LinesBean lines;

                        public ToplineBean getTopline() {
                            return topline;
                        }

                        public void setTopline(ToplineBean topline) {
                            this.topline = topline;
                        }

                        public LinesBean getLines() {
                            return lines;
                        }

                        public void setLines(LinesBean lines) {
                            this.lines = lines;
                        }

                        public static class ToplineBean {
                            /**
                             * key : {"hide_dash_line":1,"color":"#888888","word":"付款金额"}
                             * value : {"small_text_count":1,"color":"#000000","word":"￥0.01"}
                             */

                            private KeyBean key;
                            private ValueBean value;

                            public KeyBean getKey() {
                                return key;
                            }

                            public void setKey(KeyBean key) {
                                this.key = key;
                            }

                            public ValueBean getValue() {
                                return value;
                            }

                            public void setValue(ValueBean value) {
                                this.value = value;
                            }

                            public static class KeyBean {
                                /**
                                 * hide_dash_line : 1
                                 * color : #888888
                                 * word : 付款金额
                                 */

                                private int hide_dash_line;
                                private String color;
                                private String word;

                                public int getHide_dash_line() {
                                    return hide_dash_line;
                                }

                                public void setHide_dash_line(int hide_dash_line) {
                                    this.hide_dash_line = hide_dash_line;
                                }

                                public String getColor() {
                                    return color;
                                }

                                public void setColor(String color) {
                                    this.color = color;
                                }

                                public String getWord() {
                                    return word;
                                }

                                public void setWord(String word) {
                                    this.word = word;
                                }
                            }

                            public static class ValueBean {
                                /**
                                 * small_text_count : 1
                                 * color : #000000
                                 * word : ￥0.01
                                 */

                                private int small_text_count;
                                private String color;
                                private String word;

                                public int getSmall_text_count() {
                                    return small_text_count;
                                }

                                public void setSmall_text_count(int small_text_count) {
                                    this.small_text_count = small_text_count;
                                }

                                public String getColor() {
                                    return color;
                                }

                                public void setColor(String color) {
                                    this.color = color;
                                }

                                public String getWord() {
                                    return word;
                                }

                                public void setWord(String word) {
                                    this.word = word;
                                }
                            }
                        }

                        public static class LinesBean {
                            private List<LineBean> line;

                            public List<LineBean> getLine() {
                                return line;
                            }

                            public void setLine(List<LineBean> line) {
                                this.line = line;
                            }

                            public static class LineBean {
                                /**
                                 * key : {"color":"#888888","word":"收款方"}
                                 * value : {"color":"#000000","word":"🎾 🎾 🎾 🎾 🎾 🎾"}
                                 */

                                private KeyBeanX key;
                                private ValueBeanX value;

                                public KeyBeanX getKey() {
                                    return key;
                                }

                                public void setKey(KeyBeanX key) {
                                    this.key = key;
                                }

                                public ValueBeanX getValue() {
                                    return value;
                                }

                                public void setValue(ValueBeanX value) {
                                    this.value = value;
                                }

                                public static class KeyBeanX {
                                    /**
                                     * color : #888888
                                     * word : 收款方
                                     */

                                    private String color;
                                    private String word;

                                    public String getColor() {
                                        return color;
                                    }

                                    public void setColor(String color) {
                                        this.color = color;
                                    }

                                    public String getWord() {
                                        return word;
                                    }

                                    public void setWord(String word) {
                                        this.word = word;
                                    }
                                }

                                public static class ValueBeanX {
                                    /**
                                     * color : #000000
                                     * word : 🎾 🎾 🎾 🎾 🎾 🎾
                                     */

                                    private String color;
                                    private String word;

                                    public String getColor() {
                                        return color;
                                    }

                                    public void setColor(String color) {
                                        this.color = color;
                                    }

                                    public String getWord() {
                                        return word;
                                    }

                                    public void setWord(String word) {
                                        this.word = word;
                                    }
                                }
                            }
                        }
                    }
                }

                public static class TemplateHeaderBean {
                    /**
                     * pay_style : 1
                     * pub_time : 1590398008
                     * shortcut_icon_url :
                     * show_icon_and_display_name : 0
                     * header_jump_url :
                     * hide_icon_and_display_name_line : 1
                     * icon_url :
                     * ignore_hide_title_and_time : 1
                     * display_name :
                     * hide_title_and_time : 1
                     * hide_time : 1
                     * first_color :
                     * title_color :
                     * title : 微信支付凭证
                     * first_data :
                     */

                    private int pay_style;
                    private int pub_time;
                    private String shortcut_icon_url;
                    private int show_icon_and_display_name;
                    private String header_jump_url;
                    private int hide_icon_and_display_name_line;
                    private String icon_url;
                    private int ignore_hide_title_and_time;
                    private String display_name;
                    private int hide_title_and_time;
                    private int hide_time;
                    private String first_color;
                    private String title_color;
                    private String title;
                    private String first_data;

                    public int getPay_style() {
                        return pay_style;
                    }

                    public void setPay_style(int pay_style) {
                        this.pay_style = pay_style;
                    }

                    public int getPub_time() {
                        return pub_time;
                    }

                    public void setPub_time(int pub_time) {
                        this.pub_time = pub_time;
                    }

                    public String getShortcut_icon_url() {
                        return shortcut_icon_url;
                    }

                    public void setShortcut_icon_url(String shortcut_icon_url) {
                        this.shortcut_icon_url = shortcut_icon_url;
                    }

                    public int getShow_icon_and_display_name() {
                        return show_icon_and_display_name;
                    }

                    public void setShow_icon_and_display_name(int show_icon_and_display_name) {
                        this.show_icon_and_display_name = show_icon_and_display_name;
                    }

                    public String getHeader_jump_url() {
                        return header_jump_url;
                    }

                    public void setHeader_jump_url(String header_jump_url) {
                        this.header_jump_url = header_jump_url;
                    }

                    public int getHide_icon_and_display_name_line() {
                        return hide_icon_and_display_name_line;
                    }

                    public void setHide_icon_and_display_name_line(int hide_icon_and_display_name_line) {
                        this.hide_icon_and_display_name_line = hide_icon_and_display_name_line;
                    }

                    public String getIcon_url() {
                        return icon_url;
                    }

                    public void setIcon_url(String icon_url) {
                        this.icon_url = icon_url;
                    }

                    public int getIgnore_hide_title_and_time() {
                        return ignore_hide_title_and_time;
                    }

                    public void setIgnore_hide_title_and_time(int ignore_hide_title_and_time) {
                        this.ignore_hide_title_and_time = ignore_hide_title_and_time;
                    }

                    public String getDisplay_name() {
                        return display_name;
                    }

                    public void setDisplay_name(String display_name) {
                        this.display_name = display_name;
                    }

                    public int getHide_title_and_time() {
                        return hide_title_and_time;
                    }

                    public void setHide_title_and_time(int hide_title_and_time) {
                        this.hide_title_and_time = hide_title_and_time;
                    }

                    public int getHide_time() {
                        return hide_time;
                    }

                    public void setHide_time(int hide_time) {
                        this.hide_time = hide_time;
                    }

                    public String getFirst_color() {
                        return first_color;
                    }

                    public void setFirst_color(String first_color) {
                        this.first_color = first_color;
                    }

                    public String getTitle_color() {
                        return title_color;
                    }

                    public void setTitle_color(String title_color) {
                        this.title_color = title_color;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public String getFirst_data() {
                        return first_data;
                    }

                    public void setFirst_data(String first_data) {
                        this.first_data = first_data;
                    }
                }

                public static class PublisherBean {
                    /**
                     * username : wxzhifu
                     * nickname : 微信支付
                     */

                    private String username;
                    private String nickname;

                    public String getUsername() {
                        return username;
                    }

                    public void setUsername(String username) {
                        this.username = username;
                    }

                    public String getNickname() {
                        return nickname;
                    }

                    public void setNickname(String nickname) {
                        this.nickname = nickname;
                    }
                }
            }
        }
    }
}
