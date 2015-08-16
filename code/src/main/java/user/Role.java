package user;

/**
 * @author Hamid Ramazani (mrramazani@gmail.com)
 *         Created on 7/27/2015
 */
public enum  Role {
    REGULAR {
        @Override
        public String toString() {
            return "عادی";
        }
    },
    MODERATOR {
        @Override
        public String toString() {
            return "مدیر";
        }
    }
}
