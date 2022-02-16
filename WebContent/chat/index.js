function sendMessage_seller() {
    var usermsg = document.getElementById("send-input-seller").value;
    var senddiv = $('#chat-message');
    var innerHtml = "<div class='sender' onclick='like()'>" + usermsg + "</div>";
    senddiv.append(innerHtml);
}

function sendMessage_buyer() {
    var usermsg = document.getElementById("send-input").value;
    var senddiv = $('#chat-message');
    var innerHtml = "<div class='receiver' onclick='like()'>" + usermsg + "</div>";
    senddiv.append(innerHtml);
}
