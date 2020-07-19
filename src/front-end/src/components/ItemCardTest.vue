<template>
  <div>
    <BasicCard @drop.native="drop" @dragover.native="allowDrop" header="1">
      <ItemCard
        draggable="true"
        imgSrc="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1594612530130&di=9ece6cdecc5222ac3352a4fc4306f15e&imgtype=0&src=http%3A%2F%2Fimg.11665.com%2Fimg01_p%2Fi1%2FT1s4plXigmHJOeH.Z9_104831.jpg"
        id="123"
        @dragstart.native="drag"
      >
        <h4>那你可牛逼了</h4>
        <p>我骚尼玛</p>
      </ItemCard>
    </BasicCard>
    <BasicCard @drop.native="drop" @dragover.native="allowDrop" header="2"></BasicCard>
  </div>
</template>

<script>
import ItemCard from "./DataCard/ItemCard";
import BasicCard from "./PanelCard/BasicCard";

export default {
  components: { ItemCard, BasicCard },
  methods: {
    allowDrop(ev) {
      ev.preventDefault();
    },
    drag(ev) {
      console.log(ev)
      ev.dataTransfer.setData("Text", ev.target.id);
    },
    drop(ev) {
      ev.preventDefault();
      var data = ev.dataTransfer.getData("Text");
      console.log(data);
      ev.target.appendChild(document.getElementById(data));
    }
  }
};
</script>

<style>
</style>