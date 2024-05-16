package kimlamdo.my_project_backend.entity;

public enum OrderStatus {
    NEW {
        @Override
        public String defaultDescription() {
            return "Đơn hàng vừa được khởi tạo";
        }
    },

    PROCESSING {
        @Override
        public String defaultDescription() {
            return "Đơn hàng đang được xử lý";
        }
    },

    PACKAGED {
        @Override
        public String defaultDescription() {
            return "Sản phẩm đã được đóng gói và chuẩn bị giao";
        }
    },

    PICKED {
        @Override
        public String defaultDescription() {
            return "Shipper đã nhận hàng";
        }
    },

    SHIPPING {
        @Override
        public String defaultDescription() {
            return "Sản phẩm đang được giao";
        }
    },

    DELIVERED {
        @Override
        public String defaultDescription() {
            return "Sản phẩm đã được giao";
        }
    },

    PAID {
        @Override
        public String defaultDescription() {
            return "Đơn hàng đã được thanh toán";
        }
    },

    CANCELED {
        @Override
        public String defaultDescription() {
            return "Sản phẩm đã bị hủy";
        }
    };

    public abstract String defaultDescription();
}

//package kimlamdo.my_project_backend.entity;
//
//public enum OrderStatus {
//    NEW {
//        @Override
//        public String defaultDescription() {
//            return "Đơn hàng vừa được khởi tạo";
//        }
//    },
//
////    CANCELED {
////        @Override
////        public String defaultDescription() {
////            return "Đơn hàng đã bị hủy";
////        }
////    },
//
//    PROCESSING {
//        @Override
//        public String defaultDescription() {
//            return "Đơn hàng đang được xử lý";
//        }
//    },
//
//    PACKAGED {
//        @Override
//        public String defaultDescription() {
//            return "Sản phẩm đã được đóng gói và chuẩn bị giao";
//        }
//    },
//
//    PICKED {
//        @Override
//        public String defaultDescription() {
//            return "Shipper đã nhận hàng";
//        }
//    },
//
//    SHIPPING {
//        @Override
//        public String defaultDescription() {
//            return "Sản phẩm đang được giao";
//        }
//    },
//
//    DELIVERED {
//        @Override
//        public String defaultDescription() {
//            return "Sản phẩm đã được giao";
//        }
//    },
//
//    RETURN_REQUESTED {
//        @Override
//        public String defaultDescription() {
//            return "Sản phẩm bị yêu cầu hoàn trả";
//        }
//    },
//
//    RETURNED {
//        @Override
//        public String defaultDescription() {
//            return "Sản phẩm đã bị hoàn trả";
//        }
//    },
//
//    PAID {
//        @Override
//        public String defaultDescription() {
//            return "Đơn hàng đã được thanh toán";
//        }
//    },
//
//    REFUNDED {
//        @Override
//        public String defaultDescription() {
//            return "Khách hàng đã được hoàn tiền";
//        }
//    };
//
//    public abstract String defaultDescription();
//}

