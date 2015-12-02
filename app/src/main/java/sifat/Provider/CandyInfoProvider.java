package sifat.Provider;

import java.util.ArrayList;
import java.util.List;

import sifat.Domain.MemoProductInfo;
import sifat.Domain.ProductInfo;

/**
 * Created by sifat on 11/14/2015.
 */
public class CandyInfoProvider implements ProductInfoProvider {

    private volatile static CandyInfoProvider candyInfoProvider;
    private static ArrayList<ProductInfo> productInfos = new ArrayList<>();
    private static List<Integer> product_images = new ArrayList<>();
    private static ArrayList<String> headers = new ArrayList<>();
    private static ArrayList<MemoProductInfo> memoProductInfos = new ArrayList<>();
    private static ArrayList<MemoProductInfo> addedProduct = new ArrayList<>();
    private static int totalItemAdded, totalCost;

    private CandyInfoProvider() {
    }

    public static CandyInfoProvider getProvider() {
        if (candyInfoProvider == null) {
            synchronized (CandyInfoProvider.class) {
                if (candyInfoProvider == null)
                    setProductInfos();
                setHeaders();
                    candyInfoProvider = new CandyInfoProvider();
            }
        }
        return candyInfoProvider;
    }

    private static void setHeaders() {
        headers.add("Bingo Milk Candy");
        headers.add("Bingo Tamarind Candy");
        headers.add("Winnie Green Mango Candy");
        headers.add("Winnie Lychee Candy");
    }

    private static void setProductInfos() {

        ProductInfo productInfo;
        MemoProductInfo memoProductInfo;


        /***
         * Bingo Milk
         * **/
        productInfo = new ProductInfo("Bingo Milk Candy", "Boyam(250)", "1 Boyam", "250 Piece", "Minimum 6 months", "Boyam MRP MRP", 250, "Per Piece MRP", 1,
                "milk_candy_banner.jpg", 1);
        product_images.add(11811);
        product_images.add(11812);
        product_images.add(11813);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        memoProductInfo = new MemoProductInfo("Bingo Milk Candy", "Boyam(250)", "1 Boyam", "Boyam", 250, 1, 1);
        memoProductInfos.add(memoProductInfo);

        productInfo = new ProductInfo("Bingo Milk Candy", "Boyam(200)", "1 Boyam", "200 Piece", "Minimum 6 months", "Boyam MRP MRP", 200, "Per Piece MRP", 1,
                "milk_candy_banner.jpg", 1);
        product_images.add(11811);
        product_images.add(11812);
        product_images.add(11813);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        memoProductInfo = new MemoProductInfo("Bingo Milk Candy", "Boyam(200)", "1 Boyam", "Boyam", 200, 1, 1);
        memoProductInfos.add(memoProductInfo);

        productInfo = new ProductInfo("Bingo Milk Candy", "Consumer Pack", "1 Pack", "50 Piece", "Minimum 6 months", "Packet MRP MRP", 40, "Per Piece MRP", 1,
                "milk_candy_banner.jpg", 1);
        product_images.add(11831);
        product_images.add(11812);
        product_images.add(11813);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        memoProductInfo = new MemoProductInfo("Bingo Milk Candy", "Consumer Pack", "12 Pack/Carton", "Carton", 480, 1, 1);
        memoProductInfos.add(memoProductInfo);

        /***
         * Bingo Tamarind
         * **/
        productInfo = new ProductInfo("Bingo Tamarind Candy", "Boyam(250)", "1 Boyam", "250 Piece", "Minimum 6 months", "Boyam MRP MRP", 250, "Per Piece MRP", 1,
                "tamarind_banner.jpg", 2);
        product_images.add(11711);
        product_images.add(11712);
        product_images.add(11713);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        memoProductInfo = new MemoProductInfo("Bingo Tamarind Candy", "Boyam(250)", "1 Boyam", "Boyam", 250, 1, 2);
        memoProductInfos.add(memoProductInfo);

        productInfo = new ProductInfo("Bingo Tamarind Candy", "Boyam(200)", "1 Boyam", "200 Piece", "Minimum 6 months", "Boyam MRP MRP", 200, "Per Piece MRP", 1,
                "tamarind_banner.jpg", 2);
        product_images.add(11711);
        product_images.add(11712);
        product_images.add(11713);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        memoProductInfo = new MemoProductInfo("Bingo Tamarind Candy", "Boyam(200)", "1 Boyam", "Boyam", 200, 1, 2);
        memoProductInfos.add(memoProductInfo);

        productInfo = new ProductInfo("Bingo Tamarind Candy", "Consumer Pack", "1 Pack", "50 Piece", "Minimum 6 months", "Packet MRP MRP", 40, "Per Piece MRP", 1,
                "tamarind_banner.jpg", 2);
        product_images.add(11731);
        product_images.add(11712);
        product_images.add(11713);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        memoProductInfo = new MemoProductInfo("Bingo Tamarind Candy", "Consumer Pack", "12 Pack/Carton", "Carton", 480, 1, 2);
        memoProductInfos.add(memoProductInfo);

        /****
         *Winnie Mango Candy
         * ***/
        productInfo = new ProductInfo("Winnie Mango Candy", "Boyam(250)", "1 Boyam", "250 Piece", "Minimum 6 months", "Boyam MRP MRP", 250, "Per Piece MRP", 1,
                "green_mango_banner.jpg", 3);
        product_images.add(11511);
        product_images.add(11512);
        product_images.add(11513);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        memoProductInfo = new MemoProductInfo("Winnie Mango Candy", "Boyam(250)", "1 Boyam", "Boyam", 250, 1, 3);
        memoProductInfos.add(memoProductInfo);

        productInfo = new ProductInfo("Winnie Mango Candy", "Boyam(200)", "1 Boyam", "200 Piece", "Minimum 6 months", "Boyam MRP MRP", 200, "Per Piece MRP", 1,
                "green_mango_banner.jpg", 3);
        product_images.add(11511);
        product_images.add(11512);
        product_images.add(11513);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        memoProductInfo = new MemoProductInfo("Winnie Mango Candy", "Boyam(200)", "1 Boyam", "Boyam", 200, 1, 3);
        memoProductInfos.add(memoProductInfo);

        productInfo = new ProductInfo("Winnie Mango Candy", "Consumer Pack", "1 Pack", "50 Piece", "Minimum 6 months", "Packet MRP MRP", 40, "Per Piece MRP", 1,
                "green_mango_banner.jpg", 3);
        product_images.add(11531);
        product_images.add(11512);
        product_images.add(11513);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        memoProductInfo = new MemoProductInfo("Winnie Mango Candy", "Consumer Pack", "12 Pack/Carton", "Carton", 480, 1, 3);
        memoProductInfos.add(memoProductInfo);


        /****
         *Winnie Lychee Candy
         * ***/
        productInfo = new ProductInfo("Winnie Lychee Candy", "Boyam(250)", "1 Boyam", "250 Piece", "Minimum 6 months", "Boyam MRP MRP", 250, "Per Piece MRP", 1,
                "lychee_banner.jpg", 4);
        product_images.add(11611);
        product_images.add(11612);
        product_images.add(11613);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        memoProductInfo = new MemoProductInfo("Winnie Lychee Candy", "Boyam(250)", "1 Boyam", "Boyam", 250, 1, 4);
        memoProductInfos.add(memoProductInfo);

        productInfo = new ProductInfo("Winnie Lychee Candy", "Boyam(200)", "1 Boyam", "200 Piece", "Minimum 6 months", "Boyam MRP MRP", 200, "Per Piece MRP", 1,
                "lychee_banner.jpg", 4);
        product_images.add(11611);
        product_images.add(11612);
        product_images.add(11613);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        memoProductInfo = new MemoProductInfo("Winnie Lychee Candy", "Boyam(200)", "1 Boyam", "Boyam", 200, 1, 4);
        memoProductInfos.add(memoProductInfo);

        productInfo = new ProductInfo("Winnie Lychee Candy", "Consumer Pack", "1 Pack", "50 Piece", "Minimum 6 months", "Packet MRP MRP", 40, "Per Piece MRP", 1,
                "lychee_banner.jpg", 4);
        product_images.add(11631);
        product_images.add(11612);
        product_images.add(11613);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        memoProductInfo = new MemoProductInfo("Winnie Lychee Candy", "Consumer Pack", "12 Pack/Carton", "Carton", 480, 1, 4);
        memoProductInfos.add(memoProductInfo);

    }

    @Override
    public ArrayList<ProductInfo> getProductInfos() {
        return productInfos;
    }

    @Override
    public ArrayList<String> getHeader() {
        return headers;
    }

    @Override
    public ArrayList<MemoProductInfo> getProductMemoInfo() {
        return memoProductInfos;
    }

    public void setProductMemoInfo(ArrayList<MemoProductInfo> memoProductInfos) {
        this.memoProductInfos = memoProductInfos;
    }

    @Override
    public int getTotalItemAdded() {
        return totalItemAdded;
    }

    public void setTotalItemAdded(int totalItemAdded) {
        this.totalItemAdded = totalItemAdded;
    }

    @Override
    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public ArrayList<MemoProductInfo> getAddedProduct() {
        return addedProduct;
    }

    public void setAddedProduct(ArrayList<MemoProductInfo> addedProduct) {
        this.addedProduct = addedProduct;
    }
}
