( function( $ ) {
	$(document).ready(function() {

		resizeLayout();
		$(window).on('load resize', function() {
			resizeLayout();
		});
	
		primaryNav();
		allMenu();
		mainBookSlider();
		
		/**
		 * 오브젝트 아이디를 확인해서 클릭시 해당 위치로 이동한다.
		 * action class : go-item
		 * location id : data-item-id
         */
		$(document).on('click', '.go-item', function(e) {
			e.preventDefault();
			var offset = $("#"+ $(this).attr("data-item-id")).offset();
			offset.top = offset.top - 120;
			$('html, body').animate({scrollTop : offset.top}, 400);
		});
		
		/**
		 * 숨겨뒀던 아이템을 보여준다.
         */
		$(document).on('click', '.show-item', function(e) {
			e.preventDefault();
	
			var item_id = "#" + $(this).attr( "data-item-id" );
	
			/**
			* 열려있는 모든 show-the-item을 닫는다.
			*/
			$(".show-the-item").addClass('display-hide');
	
			/**
			* 선택된 박스만 연다.
			*/
			$(item_id).removeClass('display-hide');
	
			// var offset = $("div[data-go-id="+$(this).attr("data-item-id")+"]").offset();
			var offset = $( item_id ).offset();
			offset.top = offset.top - 120;
	
			$('html, body').animate({scrollTop : offset.top}, 400);
		});
	
		/**
		* 열렸던 아이템을 닫는다.
		*/
		$(document).on('click', '.close-item', function() {
			var item_id = "#" + $(this).attr( "data-item-id" );
			$(item_id).addClass('display-hide');
		});
		
		$(".seat-btn button").click(function(){
			$(".seat-btn button").removeClass("selected");
			$(this).addClass("selected");
			$("#seatNum").html("디지털열람실 "+$(this).text());
			$('input:hidden[name=seatID]').val($(this).text());
			//$('input:hidden[name=seatID]').val($("#seatNum").text());
		})
		
	});

	function resizeLayout() {
		var bp_wide = 1200 - getScrollBarWidth();
		var bp_pc = 1024 - getScrollBarWidth();
		var bp_tablet = 768 - getScrollBarWidth();
		var bp_mobile = 480 - getScrollBarWidth();
		var stateBp = [0, 0, 0, 0, 0];

		var bodyWidth = $(window).width();

		if (bodyWidth > bp_wide) {
			if(!stateBp[0]){
				$('body').attr('data-responsive', 'wide');
				$(window).on('scroll', handlerScroll);
				$('.rd-search .search-result').removeClass('expand');
			}
			stateBp = [1, 0, 0, 0, 0];
		} else if(bodyWidth > bp_pc && bodyWidth <= bp_wide) {
			if(!stateBp[1]){

				$('body').attr('data-responsive', 'pc');
				$(window).on('scroll', handlerScroll);
				$('.rd-search .search-result').removeClass('expand');
			}
			stateBp = [0, 1, 0, 0, 0];
		} else if(bodyWidth > bp_tablet && bodyWidth <= bp_pc) {
			if(!stateBp[2]){
				$('body').attr('data-responsive', 'tablet');
				$(window).off('scroll', handlerScroll);
				$('.rd-search .search-result:not(.no-restrict)').addClass('expand');

				$('.foot-section .link-section').removeClass('rd-accordion').find('.panel-body').css('display', 'block');
			}
			stateBp = [0, 0, 1, 0, 0];
		} else if(bodyWidth > bp_mobile && bodyWidth <= bp_tablet) {
			if(!stateBp[3]){
				$('body').attr('data-responsive', 'mobile');
				$(window).off('scroll', handlerScroll);
				$('.rd-search .search-result:not(.no-restrict)').addClass('expand');

				$('.sub-tab-menu .tab-menu-list').css('display', 'block');
				$('.foot-section .link-section').addClass('rd-accordion').find('.panel-body').css('display', 'none');
			}
			stateBp = [0, 0, 0, 1, 0];
		} else if(bodyWidth <= bp_mobile) {
			if(!stateBp[4]){
				$('body').attr('data-responsive', 'mobile');
				$(window).off('scroll', handlerScroll);
				$('.rd-search .search-result:not(.no-restrict)').addClass('expand');

				$('.sub-tab-menu .tab-menu-list').css('display', 'none');
				$('.foot-section .link-section').addClass('rd-accordion');
			}
			stateBp = [0, 0, 0, 0, 1];
		}
	}

	function getScrollBarWidth() {
		var inner = document.createElement('p');
		inner.style.width = "100%";
		inner.style.height = "200px";

		var outer = document.createElement('div');
		outer.style.position = "absolute";
		outer.style.top = "0px";
		outer.style.left = "0px";
		outer.style.visibility = "hidden";
		outer.style.width = "200px";
		outer.style.height = "150px";
		outer.style.overflow = "hidden";
		outer.appendChild (inner);

		document.body.appendChild (outer);
		var w1 = inner.offsetWidth;
		outer.style.overflow = 'scroll';
		var w2 = inner.offsetWidth;
		if (w1 == w2) w2 = outer.clientWidth;

		document.body.removeChild (outer);

		return (w1 - w2);
	}

	function primaryNav() {
		$('#main-nav .main-nav-list > li').on('mouseenter', function() {
			$('#header').addClass('menu-open');
		});
		$('#main-nav').on('mouseleave', function() {
			$('#header').removeClass('menu-open');
		});
	}

	function allMenu() {
		$(document).on('click', '#show-menu', function() {
			$(this).addClass('active').attr('id', 'close-menu');
			$('.all-menu-area').slideDown(200);
			$('body').addClass('all-menu-open');
		});
		$(document).on('click', '#close-menu', function() {
			$(this).removeClass('active').attr('id', 'show-menu');
			$('.all-menu-area').slideUp(200);
			$('body').removeClass('all-menu-open');
		});

		if($('body').attr('data-responsive', 'mobile')) {
			var $tg = $('#all-nav > .main-nav-list > li > a');
			$tg.on('click', function(e) {
				e.preventDefault();
				//$tg.removeClass('active');
				$(this).toggleClass('active').next('.sub-menu').slideToggle(200);
			});
		}
	}

	function handlerScroll() {
		var $header = $('#header');
		var headerHeight = $header.outerHeight();

		if ($(window).scrollTop() > headerHeight) {
			$header.addClass('fixed');
		} else {
			$header.removeClass('fixed');
			$('#close-menu').click();
		}
	}

	function mainBookSlider() {
		$(".rolling-wrap .book-list").owlCarousel({
			loop: true,
			nav: true,
			dots: false,
			autoplay: true,
			autoplayTimeout: 3000,
			autoplayHoverPause: true,
			items: 3,
			margin: 5,
			responsive:{
				0:{
					items: 1,
					margin: 0
				},
				480:{
					items: 2
				},
				768:{
					items: 3
				}
			}
		})
	}
} )( jQuery );


