package content;

/**
 * @author Hamid Ramazani (mrramazani@gmail.com)
 *         Created on 7/31/2015
 */
public enum ContentLogType {
    CREATE{
        @Override
        public String toString() {
            return "ایجاد";
        }
    },
    NEW_VERSION{
        @Override
        public String toString() {
            return "ایجاد نسخه جدید";
        }
    },
    UPDATE{
        @Override
        public String toString() {
            return "به روز رسانی";
        }
    },
    DELETE{
        @Override
        public String toString() {
            return "حذف";
        }
    },
    COMMENT{
        @Override
        public String toString() {
            return "نظر";
        }
    },
    EVALUATE{
        @Override
        public String toString() {
            return "ارزیابی";
        }
    },
    VIEW {
        @Override
        public String toString() {
            return "بازدید";
        }
    }
}
