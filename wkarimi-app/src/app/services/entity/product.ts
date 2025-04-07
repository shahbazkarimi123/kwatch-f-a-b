export class Product {
    productId?: number;
    price?: number;
    uploadDate?: string;
    productName?: string;
    productTitle?: string;
    brandName?: string;
    productVendorName?: string;
    productDescription?: string;
    fabric?: string;
    material?: string;
    warranty?: number;
    feedback?: string[];
    productReplace?: boolean;
    productReturn?: boolean;
    productColor?: string[];
    productColorDetails?: string;
    category?: string;
    likes?: number;
    discount?: number;
    productLength?: number;
    productBreadth?: number;
    productHeight?: number;
    productDiagonal?: number;
    productArea?: number;
    productVolume?: number;
    productWeight?: number;
    packageWeight?: number;
    offers?: string;
    comboProduct?: boolean;
    comboProductName?: string[];
    cod?: boolean;

    // Image fields (as File)
    productFrontImg?: File;
    productBackImg?: File;
    productSide1Img?: File;
    productSide2Img?: File;
    productAboveImg?: File;

    // for base64
    productAboveImgBase64?:string;
    productBackImgBase64?:string;
    productSide2ImgBase64?:string;
    productSide1ImgBase64?:string;
    productFrontImgBase64?:string;
    
}