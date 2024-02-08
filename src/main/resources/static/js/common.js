$(document).ready(function(){
	// gnb
	$("nav > ul > li.has_sub > a").click(function(e){
		if($(this).parent().has("> ul")) {
			e.preventDefault();
		}

		if(!$(this).hasClass("on")) {
			$(this).next("ul").stop().slideDown(200);
			$(this).addClass("on");
			$(this).parent().siblings().find(" > a").removeClass("on").end().find(" > ul").stop().slideUp(200);
		}else if($(this).hasClass("on")) {
			$(this).removeClass("on");
			$(this).next("ul").stop().slideUp(200);
		}
	});

	// menu_toggle
	$(".menu_toggle").click(function(){
		$('#container .menu_toggle').toggleClass('active');
		$('body').toggleClass('snb_none');
		$(window).trigger('resize');
	});
	// cm_list
	$(".cm_list > div > a").click(function(){
		var submenu = $(this).next("div.hide_view");
		if( submenu.is(":visible") ){
			submenu.removeClass("open");
		}else{
			submenu.addClass("open");
		}
	});

	// 댓글
	$(".cm_re_info > button").click(function(){
		var submenu = $(this).parent().next("div.hide_view");
		if( submenu.is(":visible") ){
			submenu.removeClass("open");
		}else{
			submenu.addClass("open");
		}
	});

	// 첨부파일
	$(".file_input input[type='file']").on('change',function(){
		var fileName = $(this).val().split('/').pop().split('\\').pop();
		$(this).parent().siblings("input[type='text']").val(fileName);
	});
	// 파일업로드 미리보기
	$('.file_upload input[type=file]').change(function(event) {
		var tmppath = URL.createObjectURL(event.target.files[0]);
		$(this).parent('label').parent('.file_upload').parent('.file_preview').find("img").attr('src',tmppath);
	});
});

// 레이어 팝업(기본)
function layerPop(popName){
	var $layer = $("#"+ popName);
	$layer.fadeIn(500).css('display', 'inline-block').wrap( '<div class="overlay_t"></div>');
	$('body').css('overflow','hidden');
}
function layerPopClose(){
	$(".popLayer").hide().unwrap( '');
	$('body').css('overflow','auto');
	$(".popLayer video").each(function() { this.pause(); this.load(); });
}
function layerPopClose2(popName){
	$("#"+ popName).hide().unwrap( '');
	$('body').css('overflow','auto');
}

// 클릭시 새창 팝업 띄우기
function popup_win(str,id,w,h,scrollchk){
	var pop = window.open(str,id,"width="+w+",height="+h+",scrollbars="+scrollchk+",resize=no,location=no ");
	pop.focus();
}


//로그아웃
 document.addEventListener("DOMContentLoaded", function() {
            var logoutBtn = document.getElementById("logoutBtn");
            if (logoutBtn) {
                logoutBtn.addEventListener("click", function() {
                    // 로그아웃 요청을 보냄 
                    fetch('/member/logout', {
                        method:'POST',
                        headers: {
                            'Content-Type' : 'application/json'
                        }
                    }).then(response =>{
                        //리다이렉션 응답을 받으면 현재 페이지를 새로고침하여 로그인 페이지로 이동
                        if(response.redirected){
                            window.location.href = response.url;
                        }
                    }).catch(error => {
                        console.log("로그아웃 요청 중 오류 발생 : ", error);
                    });
                });
            }
        });