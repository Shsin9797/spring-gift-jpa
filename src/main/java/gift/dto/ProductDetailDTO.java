package gift.dto;

import gift.entity.Product;

public class ProductDetailDTO {
    private String name, url;
    private Long price;

    public ProductDetailDTO(String name, Long price, String url) {
        this.name = name;
        this.price = price;
        this.url = url;
    }

    public static ProductDetailDTO fromEntity(Product product) {
        return new ProductDetailDTO(product.getName(), product.getPrice(), product.getUrl());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
