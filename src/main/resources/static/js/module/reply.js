// reply.js는 모듈을 만들어두는 파일이다.
// js의 함수, 클래스를 모듈화 시켜 저장하는 공간
// 우리는 함수를 부품처럼 만들어 두고 다른 파일에서 사용할 것이다.
// 이 모듈들을 밖에서 사용할 수 있도록 내보내는 키워드가 export이다.
export function add(reply, callback) {
    $.ajax({
        url: '/replies',
        type: 'post',
        data: JSON.stringify(reply),//js객체를 json으로 형변환한다.
        contentType : 'application/json;charset=utf-8',
        success : function (){
            if(callback){
                callback();
            }
        },
        error : function (xhr, status, error){
            console.error(error)
        }
    });
}

export function getList(boardNumber, callback) {
    $.ajax({
        url: `/replies/list/${boardNumber}`,
        type: 'get',
        dataType: 'json',
        success: function (replyList) {
            if (callback) {
                callback(replyList);
            }
        },
        error: function (xhr, status, error) {
            console.error(error);
        }
    });
}

export function getListPage(boardNumber, page,callback) {
    $.ajax({
        url: `/replies/list/${boardNumber}/${page}`,
        type: 'get',
        dataType: 'json',
        success: function (replyList) {
            if (callback) {
                callback(replyList);
            }
        },
        error: function (xhr, status, error) {
            console.error(error);
        }
    });
}

export function modify(reply, callback) {
    $.ajax({
        url : `/replies/${reply.replyNumber}`,
        type : 'patch',
        data : JSON.stringify(reply),
        contentType : 'application/json;charset=utf-8',
        success : function (){
            if(callback){
                callback();
            }
        },
        error : errorHandler
    });
}

export function remove(replyNumber, callback) {
    $.ajax({
        url : `/replies/${replyNumber}`,
        type : 'delete',
        success : function (){
            if(callback){
                callback();
            }
        },
        error : errorHandler
    });
}

export function timeForToday(value){
    // new Date() 현재 날짜와 시간
    const today = new Date();
    const timeValue = new Date(value);

    console.log(today);
    console.log(timeValue);

    // Math.floor()는 소수점을 내림 처리 해준다.
    // getTime()은 1970년 01/01 을 기준으로 지금까지 몇 ms가 지났는지 알려준다.
    const betweenTime = Math.floor((today.getTime() - timeValue.getTime()) / 1000 / 60);

    console.log(betweenTime);

    if(betweenTime < 1) { return "방금 전"; }
    if(betweenTime < 60) {
        return `${betweenTime}분 전`;
    }

    const betweenTimeHour = Math.floor(betweenTime / 60);
    if(betweenTimeHour < 24){
        return `${betweenTimeHour}시간 전`;
    }

    const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
    if(betweenTimeDay < 365){
        return `${betweenTimeDay}일 전`;
    }

    return `${Math.floor(betweenTimeDay / 365)}년 전`;
}



function errorHandler(xhr, status, error) {
    console.error(error);
}







