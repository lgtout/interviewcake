package com.lagostout.interviewcake

//https://www.interviewcake.com/question/java/rectangular-love

class RectangularLove {

    static Rectangle findIntersection(Rectangle r1, Rectangle r2) {
        Rectangle intersection = null
        if (((r1.rightX >= r2.leftX && r1.leftX <= r2.rightX) ||
                (r2.rightX >= r1.leftX && r2.leftX <= r1.rightX))
                && ((r1.topY >= r2.bottomY && r1.bottomY <= r2.topY) ||
                (r2.topY >= r1.bottomY && r2.bottomY <= r1.topY))
        ) {
            int leftX = Math.max(r1.leftX, r2.leftX)
            int rightX = Math.min(r1.rightX, r2.rightX)
            int bottomY = Math.max(r1.bottomY, r2.bottomY)
            int topY = Math.min(r1.topY, r2.topY)
            intersection = new Rectangle(
                    leftX, bottomY, rightX - leftX, topY - bottomY)
        }
        intersection
    }

    static class Rectangle {

        // coordinates of bottom left corner
        Integer leftX
        Integer bottomY
        Integer rightX
        Integer topY

        // dimensions
        Integer width
        Integer height

        Rectangle(Integer leftX, Integer bottomY, Integer width, Integer height) {
            this.leftX = leftX
            this.bottomY = bottomY
            this.width  = width
            this.height = height
            this.topY = bottomY + height
            this.rightX = leftX + width
        }

        Rectangle() {}

        String toString() {
            return String.format("(%d, %d, %d, %d)", leftX, bottomY, width, height)
        }

        static Rectangle r(Integer leftX, Integer bottomY, Integer width, Integer height) {
            return new Rectangle(leftX, bottomY, width, height)
        }

        boolean equals(o) {
            if (this.is(o)) return true
            if (!(o instanceof Rectangle)) return false

            Rectangle rectangle = (Rectangle) o

            if (bottomY != rectangle.bottomY) return false
            if (height != rectangle.height) return false
            if (leftX != rectangle.leftX) return false
            if (width != rectangle.width) return false

            return true
        }

        int hashCode() {
            int result
            result = (leftX != null ? leftX.hashCode() : 0)
            result = 31 * result + (bottomY != null ? bottomY.hashCode() : 0)
            result = 31 * result + (width != null ? width.hashCode() : 0)
            result = 31 * result + (height != null ? height.hashCode() : 0)
            return result
        }

    }

}
