define([
    "jquery"
], function ($) {
    return {
        send: function (/*String*/ servletUrl, /*Object*/ sendData, /*Function*/ callBack) {
            console.log("===> send");
            $.ajax(
                {
                    url: servletUrl,
                    type: 'POST',
                    data: sendData,
                    dataType: 'json',
                    success: function (serverData) {
                        console.log(serverData);
                        callBack(serverData);
                    },
                    error: function (xhr) {
                        if (xhr.statusText != "abort" && xhr.status != 503) {
                            console.error("xhr error!!");
                        }
                    }
                });
        }
    };

});