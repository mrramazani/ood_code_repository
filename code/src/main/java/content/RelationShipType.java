package content;

/**
 * @author Hamid Ramazani (mrramazani@gmail.com)
 *         Created on 8/6/2015
 */
public enum RelationShipType {
    CONTAINMENT {
        @Override
        public String toString() {
            return "دربرگیرنده";
        }
    },
    VERSION{
        @Override
        public String toString() {
            return "نسخه ای از";
        }
    },
    RELATED{
        @Override
        public String toString() {
            return "مرتبط با";
        }
    },
    CONTRADICT{
        @Override
        public String toString() {
            return "مغایر با";
        }
    }
}
