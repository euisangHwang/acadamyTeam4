/*!
 * jQuery Upload File Plugin
 * version: 4.0.10
 * @requires jQuery v1.5 or later & form plugin
 * Copyright (c) 2013 Ravishanker Kusuma
 * http://hayageek.com/
 */
!function(e) {
    void 0 == e.fn.ajaxForm && e.getScript(("https:" == document.location.protocol ? "https://" : "http://") + "malsup.github.io/jquery.form.js");
    var a = {};
    a.fileapi = void 0 !== e("<input type='file'/>").get(0).files,
    a.formdata = void 0 !== window.FormData,
    e.fn.uploadFile = function(t) {
        function r() {
            S || (S = !0,
            function e() {
                if (w.sequential || (w.sequentialCount = 99999),
                0 == x.length && 0 == D.length)
                    w.afterUploadAll && w.afterUploadAll(C),
                    S = !1;
                else {
                    if (D.length < w.sequentialCount) {
                        var a = x.shift();
                        void 0 != a && (D.push(a),
                        a.removeClass(C.formGroup),
                        a.submit())
                    }
                    window.setTimeout(e, 100)
                }
            }())
        }
        
        function o(a, t, r) { 
        	
        	//a 버튼객체, t 설정값, r dragdrop div
        	//드래그 이벤트 관리하는 곳이다 여긴..
        	
            r.on("dragenter", function(a) {
                a.stopPropagation(),
                a.preventDefault(),
                e(this).addClass(t.dragDropHoverClass)
            }),
            r.on("dragover", function(a) {
                a.stopPropagation(),
                a.preventDefault();
                var r = e(this);
                r.hasClass(t.dragDropContainerClass) && !r.hasClass(t.dragDropHoverClass) && r.addClass(t.dragDropHoverClass)
            }),
            r.on("drop", function(r) {
                r.preventDefault(),
                e(this).removeClass(t.dragDropHoverClass),
                a.errorLog.html("");
                var o = r.originalEvent.dataTransfer.files;
              /*  alert(o.length);*/
                return !t.multiple && o.length > 1 ? void (t.showError && e("<div class='" + t.errorClass + "'>" + t.multiDragErrorStr + "</div>").appendTo(a.errorLog)) : void (0 != t.onSelect(o) && l(t, a, o))
                		
            }),
            r.on("dragleave", function(a) {
                e(this).removeClass(t.dragDropHoverClass)
            }),
            e(document).on("dragenter", function(e) {
                e.stopPropagation(),
                e.preventDefault()
            }),
            e(document).on("dragover", function(a) {
                a.stopPropagation(),
                a.preventDefault();
                var r = e(this);
                r.hasClass(t.dragDropContainerClass) || r.removeClass(t.dragDropHoverClass)
            }),
            e(document).on("drop", function(a) {
            	
                a.stopPropagation(),
                a.preventDefault(),
                e(this).removeClass(t.dragDropHoverClass)
            })
        }
        function s(e) {
            var a = ""
              , t = e / 1024;
            if (parseInt(t) > 1024) {
                var r = t / 1024;
                a = r.toFixed(2) + " MB"
            } else
                a = t.toFixed(2) + " KB";
            return a
        }
        function i(a) {
            var t = [];
            t = "string" == jQuery.type(a) ? a.split("&") : e.param(a).split("&");
            var r, o, s = t.length, i = [];
            for (r = 0; s > r; r++)
                t[r] = t[r].replace(/\+/g, " "),
                o = t[r].split("="),
                i.push([decodeURIComponent(o[0]), decodeURIComponent(o[1])]);
            return i
        }
        
        
        function l(a, t, r) { // l(o, t, f) //a는 설정값, t는 버튼 객체, r는 input에 담기는 파일객체List
        	
        	/*alert("length는?"+r.length);*/
            for (var o = 0; o < r.length; o++)
                if (n(t, a, r[o].name)) //t는 버튼객체, a는 설정값, r은 x번째 파일객체 ----------------파일 확장자 처리
                    if (a.allowDuplicates || !d(t, r[o].name))
                        if (-1 != a.maxFileSize && r[o].size > a.maxFileSize)
                            a.showError && e("<div class='" + a.errorClass + "'><b>" + r[o].name + "</b> " + a.sizeErrorStr + s(a.maxFileSize) + "</div>").appendTo(t.errorLog);
                        else if (-1 != a.maxFileCount && t.selectedFiles >= a.maxFileCount)
                            a.showError && e("<div class='" + a.errorClass + "'><b>" + r[o].name + "</b> " + a.maxFileCountErrorStr + a.maxFileCount + "</div>").appendTo(t.errorLog);
                        else {
                        	
/*                        	alert("else문 타니?");
                        	alert(t.selectedFiles);*/
                            t.selectedFiles++,
                            t.existingFileNames.push(r[o].name);		//여기는 대체 무슨작업을 하는거야 시발..
/*                            alert("existingFileNames ::: " +t);
                            console.log("t가 뭐냐 ::: "+t);
                            console.log("t.existingFileNames가 뭐냐 ::: "+t.existingFileNames); //파일이름이 그냥 담겨있네
*/                            var l = a
                              , p = new FormData
                              , u = a.fileName.replace("[]", "");     //여기서 자바스크립트 기본 설정된 fileName값이 file로 바뀌네
/*                            alert("u는 'file' 이겠찌 ::: "+u);*/
                            p.append(u, r[o]);
/*                            alert("FormData에는 u가 어떻게 들어간거지?? ::: " +p.get(u).name);*/
                            
                            var c = a.formData;
                            if (c)
                                for (var h = i(c), f = 0; f < h.length; f++) {
                                    h[f] && p.append(h[f][0], h[f][1]);
/*                                    alert(f+"번쨰 폼데이터 ::: "+p.get(h[f][0]));*/
                                }
                            l.fileData = p;
                            var w = new m(t,a) //t는 파일이름 담겨있는 버튼객체, a는 form값이 없는  설정값 ---------------화면에 파일정보 출력하는 역할
                              , g = "";
                            g = a.showFileCounter ? t.fileCounter + a.fileCounterStyle + r[o].name : r[o].name,
                            a.showFileSize && (g += " (" + s(r[o].size) + ")"),
                            w.filename.html(g);  //이자식 이거 스테이터스바 div네         ajax-file-upload-statusbar; 
                            
                            
                            var C = e("<form style='display:block; position:absolute;left: 150px;' class='" + t.formGroup + "' method='" + a.method + "' action='" + a.url + "' enctype='" + a.enctype + "'></form>");
                            C.appendTo("body"); //여기서 바닥 form을 추가
                            var b = [];
                            b.push(r[o].name),
                            v(C, l, w, b, t, r[o]), //C는 바닥 form, l은 form값 다 있는 설정 값, w는 스테이터스바 div, b는 파일이름 담긴 오브젝트,  t는 버튼 객체(파일 이름 담김), r[o]는 x번째 파일객체 -------------- 서브밋부분!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                            t.fileCounter++
                        }
                    else
                        a.showError && e("<div class='" + a.errorClass + "'><b>" + r[o].name + "</b> " + a.duplicateErrorStr + "</div>").appendTo(t.errorLog);
                else
                    a.showError && e("<div class='" + a.errorClass + "'><b>" + r[o].name + "</b> " + a.extErrorStr + a.allowedTypes + "</div>").appendTo(t.errorLog)
        }
        function n(e, a, t) { //e는 버튼객체, a는 설정값, t은 x번째 파일객체
            var r = a.allowedTypes.toLowerCase().split(/[\s,]+/g)
              , o = t.split(".").pop().toLowerCase(); //여기서 파일객체 확장자를 빼넨다.
/*            alert("파일객체를 어떻게 하는거야?"+o);*/
            return "*" != a.allowedTypes && jQuery.inArray(o, r) < 0 ? !1 : !0
        }
        function d(e, a) {
            var t = !1;
            if (e.existingFileNames.length)
                for (var r = 0; r < e.existingFileNames.length; r++)
                    (e.existingFileNames[r] == a || w.duplicateStrict && e.existingFileNames[r].toLowerCase() == a.toLowerCase()) && (t = !0);
            return t
        }
        function p(e, a) {
            if (e.existingFileNames.length)
                for (var t = 0; t < a.length; t++) {
                    var r = e.existingFileNames.indexOf(a[t]);
                    -1 != r && e.existingFileNames.splice(r, 1)
                }
        }
        function u(e, a) {
            if (e) {
                a.show();
                var t = new FileReader;
                t.onload = function(e) {
                    a.attr("src", e.target.result)
                }
                ,
                t.readAsDataURL(e)
            }
        }
        function c(a, t) { //a는 설정값, t는 버튼객체
/*        	alert("a.showFileCounter"+a.showFileCounter); //이거 디폴트가 트루
       	alert($(t).find(".ajax-file-upload-filename").length); */
            if (a.showFileCounter) {
                var r = e(t.container).find(".ajax-file-upload-filename").length;
                t.fileCounter = r + 1,
                e(t.container).find(".ajax-file-upload-filename").each(function(t, o) {
/*                	alert("여길 어떻게 오지?? 이거 없었는데?");*/
                    var s = e(this).html().split(a.fileCounterStyle)
                      , i = (parseInt(s[0]) - 1,
                    r + a.fileCounterStyle + s[1]);
                    e(this).html(i),
                    r--
                })
            }
        }
        function h(t, r, o, s) { // h(C, g, w, b) C업로드 객체, g는 업로드 클래스 값,  w는 설정 값, b는 업로드 div
        	
        	//여기서 폼하고  input생성하는 역할
            var i = "ajax-upload-id-" + (new Date).getTime()
              , d = e("<form id='aaa' method='" + o.method + "' action='" + o.url + "' enctype='" + o.enctype + "'></form>")
              , p = "<input type='file' id='" + i + "' name='" + o.fileName + "' accept='" + o.acceptFiles + "'/>";
            o.multiple && (o.fileName.indexOf("[]") != o.fileName.length - 2 && (o.fileName += "[]"),
            p = "<input type='file' id='" + i + "' name='" + o.fileName + "' accept='" + o.acceptFiles + "' multiple/>");
            var u = e(p).appendTo(d); 
          //u는 만들어진 인풋이네, 손 업로드하면 여기에 파일이 박힌다
/*            alert("nestedForms값 ::: "+o.nestedForms);*/
            //파일 올리면 돌아간다 --------------------------------------여기 내가모르는 이벤트 처리가 있다.
            u.change(function() {
                t.errorLog.html("");
                
                
                
/*                alert(o.allowedTypes);*/
                var i = (o.allowedTypes.toLowerCase().split(","), //이거 뭔지 모르겠네 시발..
                []);
                
                if (this.files) {  //이게 파일 객체네
                	
                    for (g = 0; g < this.files.length; g++)
                    	i.push(this.files[g].name);
/*                    	alert("아 이건가본데.."+i[0]);*/
/*                    	alert(this.files[0].name); //여기 파일네임 찍히네. --------------
                    alert(0 == o.onSelect(this.files)); //이거 false
*/                    
                    if (0 == o.onSelect(this.files))
                        return
                } else {
                    var p = e(this).val()
                      , u = [];
                    if (i.push(p),
                    !n(t, o, p))
                        return void (o.showError && e("<div class='" + o.errorClass + "'><b>" + p + "</b> " + o.extErrorStr + o.allowedTypes + "</div>").appendTo(t.errorLog));
                    if (u.push({
                        name: p,
                        size: "NA"
                    }),
                    0 == o.onSelect(u))
                        return
                }
                if (c(o, t), 
                s.unbind("click"),
                d.hide(),                            //폼을 가려버리네
                h(t, r, o, s),
                d.addClass(r),
                o.serialize && a.fileapi && a.formdata) {
                	
/*                	alert("1번이냐");*/
                    d.removeClass(r);
                    var f = this.files; //파일을 담아서 보내네??
                    d.remove(),
                    l(o, t, f) //o는 설정값, t는 버튼 객체, f는 input에 담기는 파일객체List -------------------------점프 투 l함수 --스테이터스 바 추가, 및 formData추가 (설정값.fileData에) => 서브밋까지하고 옴
                } else {
/*                	alert("2번이냐");*/
                    for (var w = "", g = 0; g < i.length; g++)
                        w += o.showFileCounter ? t.fileCounter + o.fileCounterStyle + i[g] + "<br>" : i[g] + "<br>",
                        t.fileCounter++;
                    if (-1 != o.maxFileCount && t.selectedFiles + i.length > o.maxFileCount)
                        return void (o.showError && e("<div class='" + o.errorClass + "'><b>" + w + "</b> " + o.maxFileCountErrorStr + o.maxFileCount + "</div>").appendTo(t.errorLog));
                    t.selectedFiles += i.length;
                    var C = new m(t,o);
                    C.filename.html(w),
                    v(d, o, C, i, t, null)
                }
            }),
            o.nestedForms ? (d.css({
                margin: 0,
                padding: 0
            }),
            s.css({
                position: "relative",
                overflow: "hidden",
                cursor: "default"
            }),
            u.css({
                position: "absolute",
                cursor: "pointer",
                top: "0px",
                width: "100%",
                height: "100%",
                left: "0px",
                "z-index": "100",
                opacity: "0.0",
                filter: "alpha(opacity=0)",
                "-ms-filter": "alpha(opacity=0)",
                "-khtml-opacity": "0.0",
                "-moz-opacity": "0.0"
            }),
            d.appendTo(s)) : (d.appendTo(e("body")),			//폼을 여기서 div에 어펜드한다.  //그리고 바디에도 하나더 어펜드한다
            d.css({
                margin: 0,
                padding: 0,
                display: "block",
                position: "absolute",
                left: "-250px"
            }),
            -1 != navigator.appVersion.indexOf("MSIE ") ? s.attr("for", i) : s.click(function() {
                u.click()
            }))
        }
        function f(a, t) { //a는 파일이름 담겨있는 버튼객체, t는 form값이 없는  설정값
            return this.statusbar = e("<div class='ajax-file-upload-statusbar'></div>").width(t.statusBarWidth),
            this.preview = e("<img class='ajax-file-upload-preview' />").width(t.previewWidth).height(t.previewHeight).appendTo(this.statusbar).hide(),
            this.filename = e("<div class='ajax-file-upload-filename'></div>").appendTo(this.statusbar),
            this.progressDiv = e("<div class='ajax-file-upload-progress'>").appendTo(this.statusbar).hide(),
            this.progressbar = e("<div class='ajax-file-upload-bar'></div>").appendTo(this.progressDiv),
            this.abort = e("<div>" + t.abortStr + "</div>").appendTo(this.statusbar).hide(),
            this.cancel = e("<div>" + t.cancelStr + "</div>").appendTo(this.statusbar).hide(),
            this.done = e("<div>" + t.doneStr + "</div>").appendTo(this.statusbar).hide(),
            this.download = e("<div>" + t.downloadStr + "</div>").appendTo(this.statusbar).hide(),
            this.del = e("<div>" + t.deletelStr + "</div>").appendTo(this.statusbar).hide(),
            this.abort.addClass("ajax-file-upload-red"),
            this.done.addClass("ajax-file-upload-green"),
            this.download.addClass("ajax-file-upload-green"),
            this.cancel.addClass("ajax-file-upload-red"),
            this.del.addClass("ajax-file-upload-red"),
            this
        }
        function m(a, t) { //a는 파일이름 담겨있는 버튼객체, t는 form값이 없는  설정값
            var r = null;
/*            alert("customProgressBar ::: "+t.customProgressBar);*/
            return r = t.customProgressBar ? new t.customProgressBar(a,t) : new f(a,t),
            r.abort.addClass(a.formGroup),
            r.abort.addClass(t.abortButtonClass),
            r.cancel.addClass(a.formGroup),
            r.cancel.addClass(t.cancelButtonClass),
            t.extraHTML && (r.extraHTML = e("<div class='extrahtml'>" + t.extraHTML() + "</div>").insertAfter(r.filename)),
            "bottom" == t.uploadQueueOrder ? e(a.container).append(r.statusbar) : e(a.container).prepend(r.statusbar),
            r
        }
        function v(t, o, s, l, n, d) { //t는 바닥 form, o은 form값 다 있는 설정 값, s는 스테이터스바 div, l는 파일이름 담긴 오브젝트,  n는 버튼 객체(파일 이름 담김), d[o]는 x번째 파일객체

/*  내가 추가한 소스                 
        	
    	if (n.selectedFiles < 2) {
    		
            alert("값 넣기 시작");
            alert("selectedFiles ::: "+n.selectedFiles);
            var selects = $("#enrollFrm select");
    		
    		var selected = "";
    		
    		$.each(selects, function(index) {
    			var Eselect = this;
    			
    			var options = $(this).children("option");
    			
    			$.each(options, function(index) {
    				
    				if($(Eselect).attr("name") == "MG" || $(Eselect).attr("name") == "ME") {
    				
    					selected = $(this).is(":selected");
    					
    					if(selected) {
    						o.fileData.append($(Eselect).attr("name"), $(this).val());
    					}
    				}	
    			});
    		});
    		
    		var inputs = $("#enrollFrm input");
     		
    		$.each(inputs, function(index) {
    			
    			if($(this).attr("name") != "undefined" && $(this).val() != "") {
    				
    				o.fileData.append($(this).attr("name"), $(this).val());
    			}
    		});
    	}	
 내가 추가한 소스        	*/
            var h = {
                cache: !1,
                contentType: !1,
                processData: !1,
                forceSync: !1,
                type: o.method,
                data: o.formData,
                formData: o.fileData,
                dataType: o.returnType,
                beforeSubmit: function(a, r, d) {
                    if (0 != o.onSubmit.call(this, l)) {
                        if (o.dynamicFormData) {
                            var u = i(o.dynamicFormData());
                            if (u)
                                for (var h = 0; h < u.length; h++)
                                    u[h] && (void 0 != o.fileData ? d.formData.append(u[h][0], u[h][1]) : d.data[u[h][0]] = u[h][1])
                        }
                        return o.extraHTML && e(s.extraHTML).find("input,select,textarea").each(function(a, t) {
                            void 0 != o.fileData ? d.formData.append(e(this).attr("name"), e(this).val()) : d.data[e(this).attr("name")] = e(this).val()
                        }),
                        !0
                    }
                    return s.statusbar.append("<div class='" + o.errorClass + "'>" + o.uploadErrorStr + "</div>"),
                    s.cancel.show(),
                    t.remove(),
                    s.cancel.click(function() {
                        x.splice(x.indexOf(t), 1),
                        p(n, l),
                        s.statusbar.remove(),
                        o.onCancel.call(n, l, s),
                        n.selectedFiles -= l.length,
                        c(o, n)
                    }),
                    !1
                },
                beforeSend: function(e, t) {
                    s.progressDiv.show(),
                    s.cancel.hide(),
                    s.done.hide(),
                    o.showAbort && (s.abort.show(),
                    s.abort.click(function() {
                        p(n, l),
                        e.abort(),
                        n.selectedFiles -= l.length,
                        o.onAbort.call(n, l, s)
                    })),
                    a.formdata ? s.progressbar.width("1%") : s.progressbar.width("5%")
                },
                uploadProgress: function(e, a, t, r) {
                    r > 98 && (r = 98);
                    var i = r + "%";
                    r > 1 && s.progressbar.width(i),
                    o.showProgress && (s.progressbar.html(i),
                    s.progressbar.css("text-align", "center"))
                },
                success: function(a, r, i) {
                    if (s.cancel.remove(),
                    D.pop(),
                    "json" == o.returnType && "object" == e.type(a) && a.hasOwnProperty(o.customErrorKeyStr)) {
                        s.abort.hide();
                        var d = a[o.customErrorKeyStr];
                        return o.onError.call(this, l, 200, d, s),
                        o.showStatusAfterError ? (s.progressDiv.hide(),
                        s.statusbar.append("<span class='" + o.errorClass + "'>ERROR: " + d + "</span>")) : (s.statusbar.hide(),
                        s.statusbar.remove()),
                        n.selectedFiles -= l.length,
                        void t.remove()
                    }
                    n.responses.push(a),
                    s.progressbar.width("100%"),
                    o.showProgress && (s.progressbar.html("100%"),
                    s.progressbar.css("text-align", "center")),
                    s.abort.hide(),
                    o.onSuccess.call(this, l, a, i, s),
                    o.showStatusAfterSuccess ? (o.showDone ? (s.done.show(),
                    s.done.click(function() {
                        s.statusbar.hide("slow"),
                        s.statusbar.remove()
                    })) : s.done.hide(),
                    o.showDelete ? (s.del.show(),
                    s.del.click(function() {
                        p(n, l),
                        s.statusbar.hide().remove(),
                        o.deleteCallback && o.deleteCallback.call(this, a, s),
                        n.selectedFiles -= l.length,
                        c(o, n)
                    })) : s.del.hide()) : (s.statusbar.hide("slow"),
                    s.statusbar.remove()),
                    o.showDownload && (s.download.show(),
                    s.download.click(function() {
                        o.downloadCallback && o.downloadCallback(a)
                    })),
                    t.remove()
                },
                error: function(e, a, r) {
                    s.cancel.remove(),
                    D.pop(),
                    s.abort.hide(),
                    "abort" == e.statusText ? (s.statusbar.hide("slow").remove(),
                    c(o, n)) : (o.onError.call(this, l, a, r, s),
                    o.showStatusAfterError ? (s.progressDiv.hide(),
                    s.statusbar.append("<span class='" + o.errorClass + "'>ERROR: " + r + "</span>")) : (s.statusbar.hide(),
                    s.statusbar.remove()),
                    n.selectedFiles -= l.length),
                    t.remove()
                }
            };
            o.showPreview && null != d && "image" == d.type.toLowerCase().split("/").shift() && u(d, s.preview),
            o.autoSubmit ? (t.ajaxForm(h),
            x.push(t),
            r()) : (o.showCancel && (s.cancel.show(),
            s.cancel.click(function() {
                x.splice(x.indexOf(t), 1),
                p(n, l),
                t.remove(),
                s.statusbar.remove(),
                o.onCancel.call(n, l, s),
                n.selectedFiles -= l.length,
                c(o, n)
            })),
            t.ajaxForm(h))
        }
        var w = e.extend({
            url: "",
            method: "POST",
            enctype: "multipart/form-data",
            returnType: null,
            allowDuplicates: !0,
            duplicateStrict: !1,
            allowedTypes: "*",
            acceptFiles: "*",
            fileName: "file",
            formData: !1,
            dynamicFormData: !1,
            maxFileSize: -1,
            maxFileCount: -1,
            multiple: !0,
            dragDrop: !0,
            autoSubmit: !0,
            showCancel: !0,
            showAbort: !0,
            showDone: !1,
            showDelete: !1,
            showError: !0,
            showStatusAfterSuccess: !0,
            showStatusAfterError: !0,
            showFileCounter: !0,
            fileCounterStyle: "). ",
            showFileSize: !0,
            showProgress: !1,
            nestedForms: !0,
            showDownload: !1,
            onLoad: function(e) {},
            onSelect: function(e) {
                return !0
            },
            onSubmit: function(e, a) {},
            onSuccess: function(e, a, t, r) {},
            onError: function(e, a, t, r) {},
            onCancel: function(e, a) {},
            onAbort: function(e, a) {},
            downloadCallback: !1,
            deleteCallback: !1,
            afterUploadAll: !1,
            serialize: !0,
            sequential: !1,
            sequentialCount: 2,
            customProgressBar: !1,
            abortButtonClass: "ajax-file-upload-abort",
            cancelButtonClass: "ajax-file-upload-cancel",
            dragDropContainerClass: "ajax-upload-dragdrop",
            dragDropHoverClass: "state-hover",
            errorClass: "ajax-file-upload-error",
            uploadButtonClass: "ajax-file-upload",
            dragDropStr: "<span><b>Drag &amp; Drop Files</b></span>",
            uploadStr: "Upload",
            abortStr: "Abort",
            cancelStr: "Cancel",
            deletelStr: "Delete",
            doneStr: "Done",
            multiDragErrorStr: "Multiple File Drag &amp; Drop is not allowed.",
            extErrorStr: "is not allowed. Allowed extensions: ",
            duplicateErrorStr: "is not allowed. File already exists.",
            sizeErrorStr: "is not allowed. Allowed Max size: ",
            uploadErrorStr: "Upload is not allowed",
            maxFileCountErrorStr: " is not allowed. Maximum allowed files are:",
            downloadStr: "Download",
            customErrorKeyStr: "jquery-upload-file-error",
            showQueueDiv: !1,
            statusBarWidth: 400,
            dragdropWidth: 400,
            showPreview: !1,
            previewHeight: "auto",
            previewWidth: "100%",
            extraHTML: !1,
            uploadQueueOrder: "top"
        }, t);
        this.fileCounter = 1,
        this.selectedFiles = 0;
        var g = "ajax-file-upload-" + (new Date).getTime();
        this.formGroup = g,
        this.errorLog = e("<div></div>"),
        this.responses = [],
        this.existingFileNames = [],
        a.formdata || (w.dragDrop = !1),
        a.formdata || (w.multiple = !1),
        e(this).html("");
        var C = this 
          , b = e("<div>" + w.uploadStr + "</div>");
        e(b).addClass(w.uploadButtonClass),
        
        
        //C가 업로드버튼 객체
        
        
        
        function F() {
        	
/*        alert("여기맞어33??"+$(C).attr("id"));	*/
        	
            if (e.fn.ajaxForm) {
        
            	
            	
            	//일루오지
                if (w.dragDrop) {
                	
                    var a = e('<div class="' + w.dragDropContainerClass + '" style="vertical-align:top;"></div>').width(w.dragdropWidth);
                    e(C).append(a),
                 // <div> class="ajax-upload-dragdrop" 생성 
                    e(a).append(b),
                    //Upload <div>생성 / 클래스 추가 : ajax-file-upload   
                    e(a).append(e(w.dragDropStr)),
                    //span 추가
                    o(C, w, a) //버튼객체, 설정값, dragdrop div
                    
                } else {
                    e(C).append(b);}
                
                e(C).append(C.errorLog),
                
                //w.showQueueDiv : false;
                w.showQueueDiv ? C.container = e("#" + w.showQueueDiv) : C.container = e("<div class='ajax-file-upload-container'></div>").insertAfter(e(C)),
                		
                //<div> ajax-file-upload-container생성		
                		
                w.onLoad.call(this, C),
                h(C, g, w, b) //암튼, form하고 input찍어낸다.
                //C업로드 객체, g는 업로드 클래스 값,  w는 설정 값, a는  form들어있는 dragDrop - 이벤트 처리, b는 업로드 div
                
            } else
                window.setTimeout(F, 10)
        }(),
        this.startUpload = function() {
            e("form").each(function(a, t) {
                e(this).hasClass(C.formGroup) && x.push(e(this))
            }),
            x.length >= 1 && r()
        }
        ,
        this.getFileCount = function() {
            return C.selectedFiles
        }
        ,
        this.stopUpload = function() {
            e("." + w.abortButtonClass).each(function(a, t) {
                e(this).hasClass(C.formGroup) && e(this).click()
            }),
            e("." + w.cancelButtonClass).each(function(a, t) {
                e(this).hasClass(C.formGroup) && e(this).click()
            })
        }
        ,
        this.cancelAll = function() {
            e("." + w.cancelButtonClass).each(function(a, t) {
                e(this).hasClass(C.formGroup) && e(this).click()
            })
        }
        ,
        this.update = function(a) {
            w = e.extend(w, a)
        }
        ,
        this.reset = function(e) {
            C.fileCounter = 1,
            C.selectedFiles = 0,
            C.errorLog.html(""),
            0 != e && C.container.html("")
        }
        ,
        this.remove = function() {
            C.container.html(""),
            e(C).remove()
        }
        ,
        this.createProgress = function(seqNum, e, path, t) { //seqNum, 이름, 패스, 사이즈
            var r = new m(this,w);
            alert(path);
            r.progressDiv.show(),
            r.progressbar.width("100%");
            var o = "";
            return o = w.showFileCounter ? C.fileCounter + w.fileCounterStyle + e : e,
            w.showFileSize && (o += " (" + s(t) + ")"),
            r.filename.html(o),
            C.fileCounter++,
            C.selectedFiles++,
            w.showPreview && (r.preview.attr("src", path),
            r.preview.show()),
            w.showDownload && (r.download.show(),
            r.download.click(function() {
                w.downloadCallback && w.downloadCallback.call(C, [e])
            })),
            w.showDelete && (r.del.show(),
            r.del.click(function() {
                r.statusbar.hide().remove();
                var a = [e];
                w.deleteCallback && w.deleteCallback.call(this, seqNum, path),
                C.selectedFiles -= 1,
                c(w, C)
            })),
            r
        }
        ,
        this.getResponses = function() {
            return this.responses
        }
        ;
        var x = []
          , D = []
          , S = !1;
        return this
    }
}(jQuery);
