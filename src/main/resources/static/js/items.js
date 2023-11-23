const createDateDesSortOption = 'createdDate,desc';

function formatPrice() {
    $(".item-price").each(function () {
        let itemPrice = $(this).text(); // 현재 요소의 텍스트 가져오기
        let formattedPrice = parseFloat(itemPrice).toLocaleString('en-US') + ' 원';
        $(this).text(formattedPrice); // 형식화된 가격으로 업데이트
    });
}

function formatInputPrice(price) {
    return parseFloat(price).toLocaleString('en-US' + ' 원');
}

function loadData() {

    let nowPage = parseInt($("#nowPage").val(), 10);
    let selectedSortOption = $('#selectOption').val();
    let searchParam = $('#searchParam').val();
    let pageSize = $('#pageSize').val();

    let isLoading = false;

    if (isLoading) return;

    let params = [
        'page=' + (nowPage + 1),
        'size=' + pageSize,
        'searchParam=' + searchParam,
        'sort=' + selectedSortOption,
        'sort=' + createDateDesSortOption,
    ].join('&');

    isLoading = true;

    $.ajax({
        url: '/api/items?' + params,
        method: 'GET',
        success: function (data) {
            let content = data.content;
            if (content.length > 0) {
                content.forEach(function (item) {
                    let productCard = '<div class="product-card">' +
                        '<a href="/items/' + item.id + '">' +
                        '<img src=' + item.imagePath + ' alt="Product">' +
                        '<div class="product-info">' +
                        '<h2>' + item.name + '</h2>' +
                        '<p class="item-price">' + item.price.toLocaleString() + ' 원</p>';

                        // 품절 여부에 따라 품절 텍스트 추가
                        if (item.soldOut) {
                            productCard += '<p class="sold-out-message">품절</p>';
                        }

                        // 상품 카드 HTML 마무리
                        productCard += '</div>' +
                            '</a>' +
                            '</div>';
                    $('.product-container').append(productCard);
                });
            }
            if (data.last === true) {
                $('#loadMoreButton').hide();
            } else {
                $("#nowPage").val(nowPage + 1);
            }

            isLoading = false;
        },
        error: function () {
            console.error('error');
            isLoading = false;
        },
    });

}

function chooseSortOption() {
    let nowPage = 0;
    let selectedSortOption = $('#selectOption').val();
    let searchParam = $('#searchParam').val();
    let pageSize = $('#pageSize').val();

    let params = [
        'page=' + nowPage,
        'size=' + pageSize,
        'searchParam=' + searchParam,
        'sort=' + selectedSortOption,
        'sort=' + createDateDesSortOption
    ].join('&');

    $.ajax({
        url: '/api/items?' + params,
        method: 'GET',
        success: function (data) {
            // 기존의 내용 지우기
            $('.product-container').empty();

            let content = data.content;
            if (content.length > 0) {
                content.forEach(function (item) {
                    let productCard = '<div class="product-card">' +
                        '<a href="/items/' + item.id + '">' +
                        '<img src=' + item.imagePath + ' alt="Product">' +
                        '<div class="product-info">' +
                        '<h2>' + item.name + '</h2>' +
                        '<p class="item-price">' + item.price.toLocaleString() + ' 원</p>';

                        // 품절 여부에 따라 품절 텍스트 추가
                        if (item.soldOut) {
                            productCard += '<p class="sold-out-message">품절</p>';
                        }

                        // 상품 카드 HTML 마무리
                        productCard += '</div>' +
                            '</a>' +
                            '</div>';
                    $('.product-container').append(productCard);
                });
            }

            if (data.last === true) {
                $('#loadMoreButton').hide();
            } else {
                $('#loadMoreButton').show();
                $('#nowPage').val(data.pageable.pageNumber);
            }

        },
        error: function () {
            console.error('error');
        },
    });
}


$(document).ready(function () {

    formatPrice();

    $('#loadMoreButton').on('click', function () {
        loadData();
    });

    $('#selectOption').on('change', function () {
        chooseSortOption();
    });
});

