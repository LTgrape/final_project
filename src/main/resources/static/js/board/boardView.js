import * as reply from '../module/reply.js';

let boardNumber = $('.board-num').val();
let page = 1;


$('.btn-reply').on('click', function (){
    page = 1;
    let content = $('#reply-content').val();

    let replyObj = {
        replyContent : content,
        boardNumber : boardNumber
    };

    reply.add(replyObj, ()=>{
        reply.getListPage(boardNumber, page, showReply);
    });

    $('#reply-content').val('');
});

// =====================================================

//무한 스크롤 페이징
$(window).on('scroll', function (){
    // 현재 브라우저의 스크롤 위치를 의미
    console.log(`ScrollTop : ${ $(window).scrollTop() }` )
    // 문서 전체의 높이를 의미
    console.log(`document height : ${ $(document).height() }` )
    // 브라우저 화면의 높이를 의미
    console.log(`window height : ${ $(window).height() }` )

//    [현재 브라우저 스크롤의 위치 == 문서높이 - 화면 높이] -> 문서 마지막에 스크롤이 도착함
    if($(window).scrollTop() == $(document).height() - $(window).height()) {
        console.log('!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!');
        console.log(++page);
        reply.getListPage(boardNumber, page, appendReply);
    }
});

reply.getListPage(boardNumber, page, showReply);

function showReply(replyMap) {
    console.log(replyMap);

    let resultHtml = '';

    replyMap.replyList.forEach(r => {
        resultHtml += `
            <div class="reply" data-num="${r.replyNumber}">
              <div class="reply-box">
                <div class="reply-box__writer">${r.userId}</div>
                <div class="reply-box__content">${r.replyContent}</div>
              </div>
              <div class="reply-time">
                ${ reply.timeForToday(r.replyUpdateDate)}
              </div>
              
              <div class="reply-btn-box">
                ${r.userNumber == loginNumber ?
            `<span class="reply-btns"></span>
                <div class="reply-btns__box none">
                  <div class="reply-remove-btn">삭제</div>
                  <div class="reply-modify-btn">수정</div>
                </div>` : ''}
                
              </div>
            </div>
        `;
    });

    $('.reply-list-wrap').html(resultHtml);
}

function appendReply(replyMap) {
    console.log(replyMap);

    let resultHtml = '';

    replyMap.replyList.forEach(r => {
        resultHtml += `
            <div class="reply" data-num="${r.replyNumber}">
              <div class="reply-box">
                <div class="reply-box__writer">${r.userId}</div>
                <div class="reply-box__content">${r.replyContent}</div>
              </div>
              <div class="reply-time">
                ${ reply.timeForToday(r.replyUpdateDate)}
              </div>
              
              <div class="reply-btn-box">
                ${r.userNumber == loginNumber ?
            `<span class="reply-btns"></span>
                <div class="reply-btns__box none">
                  <div class="reply-remove-btn">삭제</div>
                  <div class="reply-modify-btn">수정</div>
                </div>` : ''}
                
              </div>
            </div>
        `;
    });

    $('.reply-list-wrap').append(resultHtml);
}

//======================================================================

$('.reply-list-wrap').on('click', '.reply-btns', function () {
    let $replyBtnBox = $(this).closest('.reply-btn-box').find('.reply-btns__box');

    $replyBtnBox.toggleClass('none');
});

$('body').click(function (e) {
    if ($(e.target).hasClass('reply-btns')) {
        //console.log('aa');
        return;
    }
    if (!$('.reply-btns__box').has(e.target).length) {
        $('.reply-btns__box').addClass('none');
    }
});

$('.btn-back').on('click', function () {
    window.location.href = '/board/list';
})

// 수정 버튼
$('.btn-modify').on('click', function () {
    let boardNumber = $('.board-num').val();
    window.location.href = '/board/modify?boardNumber=' + boardNumber;
});
// 삭제 버튼
$('.btn-remove').on('click', function () {
    let boardNumber = $('.board-num').val();
    window.location.href = '/board/remove?boardNumber=' + boardNumber;
});

// 리플 삭제 버튼 처리
$('.reply-list-wrap').on('click', '.reply-remove-btn', function () {
    $('.reply-btns__box').addClass('none');

    let replyNumber = $(this).closest('.reply').data('num');

    reply.remove(replyNumber, () => {
        reply.getListPage(boardNumber, page, showReply);
    });
});

// 리플 수정 버튼 처리
$('.reply-list-wrap').on('click', '.reply-modify-btn', function () {
    let $content = $(this).closest('.reply').find('.reply-box__content');
    $content.replaceWith(`
  <div class='modify-box'>
    <textarea class='modify-content'>${$content.text()}</textarea>
    <button type='button' class='modify-content-btn'>수정 완료</button>
  </div>
  `);
    $('.reply-btns__box').addClass('none');
});

// 리플 수정 완료 처리
$('.reply-list-wrap').on('click', '.modify-content-btn', function () {
    console.log('modify!!!');

    let replyNumber = $(this).closest('.reply').data('num');
    let replyContent = $(this).closest('.modify-box').find('.modify-content').val();
    let replyObj = {
        replyNumber : replyNumber,
        replyContent : replyContent
    }

    reply.modify(replyObj, () => {
        reply.getListPage(boardNumber, page, showReply);
    });
});


// 이미지 띄우기 처리

displayImgs();

function displayImgs() {
    let boardNumber = $('.board-num').val();

    $.ajax({
        url: '/files/imgs',
        type: 'get',
        data: {boardNumber: boardNumber},
        success: function (resp) {
            // console.log(resp);
            // console.log(resp[0].fileName);
            // console.log(resp[1]);
            let html = '';

            resp.forEach(file => {
                let fileName = file.fileUploadPath + '/' + file.fileUuid + "_" + file.fileName;
                html += `<a href="/files/download?fileName=${fileName}">
                        <img src="/files/display?fileFullName=${fileName}">
                        </a>`
            });

            $('.post-images').html(html);
        },
        error: function (xhr, status, error) {
            console.error(error);
        }
    });
}