package user;

/**
 * @author Hamid Ramazani (mrramazani@gmail.com)
 *         Created on 7/31/2015
 */
public enum ActivityType {
    CREATE{
        @Override
        public String toString() {
            return "ایجاد محتوا";
        }
    },
    UPDATE {
        @Override
        public String toString() {
            return "به روزرسانی محتوا";
        }
    },
    DELETE{
        @Override
        public String toString() {
            return "حذف محتوا";
        }
    },
    COMMENT {
        @Override
        public String toString() {
            return "نظردهی برای محتوا";
        }
    },
    NEW_CONTENT_VERSION {
        @Override
        public String toString() {
            return "افزودن نسخه جدید";
        }
    },
    CHANGE_SCORE{
        @Override
        public String toString() {
            return "امتیاز دهی به کاربر";
        }
    },
    EVALUATE {
        @Override
        public String toString() {
            return "ارزیابی محتوا";
        }
    },
    VIEW {
        @Override
        public String toString() {
            return "مشاهده محتوا";
        }
    }

}
