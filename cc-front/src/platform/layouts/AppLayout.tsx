import { computed, defineComponent } from 'vue';
import { useStore } from 'vuex';
import { individuationConst } from '../commons/consts/individuation-const';
import DeskLayout from './DeskLayout.vue';
import AdminLayout from './AdminLayout.vue';

export default defineComponent({
  name: 'AppLayout',
  components:{
    DeskLayout,
    AdminLayout
  },
  setup(props) {
    const store = useStore();
    const appLayoutState = computed(() => {
        return store.getters['appLayoutState/getLayoutState'];
    });

    const getLayout = () => {
        switch (appLayoutState.value) {
            case individuationConst.layout.deskLayout:
                return (<DeskLayout />);
            default:
                return (<AdminLayout/>)
        }
    }

    return () => (
      <>
        {
            getLayout()
        }
      </>
    );
  }
});
