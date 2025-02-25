<template>
  <div class="app-container home">
    <div class="image-container">
      <img src="../assets/images/img.png" alt="Image 1">
      <img src="../assets/images/img_1.png" alt="Image 2">
    </div>
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {};
  },
  methods: {}
};
</script>

<style scoped lang="scss">
.app-container.home {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh; /* 让容器占据整个视口高度 */
}

.image-container {
  display: flex;
  gap: 20px; /* 图片之间的间距 */
  max-width: 100%; /* 确保容器不会超出视口宽度 */
  padding: 20px; /* 容器内边距 */
}

.image-container img {
  max-width: 100%; /* 图片最大宽度不超过容器宽度 */
  height: auto; /* 保持图片比例 */
  border-radius: 10px; /* 图片圆角 */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 图片阴影 */
  transition: transform 0.3s ease; /* 图片缩放过渡效果 */

  &:hover {
    transform: scale(1.05); /* 鼠标悬停时图片放大 */
  }
}
</style>
