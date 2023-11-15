$(document).ready(function () {
    let nowPage = $("#nowPage").val();

    let isLoading = false;

    function loadData() {
        if(isLoading) return;

        let params = new URLSearchParams({
            page : nowPage + 1,
            size : 20,
            sort: 'createdDate,desc'
        });

        isLoading = true;

        $.ajax({
            url: '/api/items?'+params.toString(),
            method: 'GET',
            success: function (data) {
                let content = data.content;
                if (content.length > 0) {
                    content.forEach(function (item) {
                        let productCard = '<div class="product-card">' +
                            '<a href="/items/' + item.id + '">' +
                            '<img alt="Product">' +
                            '<div class="product-info">' +
                            '<h2>' + item.name + '</h2>' +
                            '<p>가격: $' + item.price + '</p>' +
                            '</div>' +
                            '</a>' +
                            '</div>';
                        $('.product-container').append(productCard);
                    });
                }
                if (data.last === true) {
                    $('#loadMoreButton').hide();
                }
                ++nowPage;
                console.log(data);
                isLoading = false;
            },
            error: function () {
                console.error('error');
                isLoading = false;
            },
        })

    }

    $('#loadMoreButton').on('click', function () {
        loadData();
    });

});

