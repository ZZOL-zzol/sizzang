import SmallButton from "../components/common/SmallButton";
import { useSelector } from "react-redux/es/hooks/useSelector";
import { useDispatch } from "react-redux";
import {plusTest} from '../store'

const MainPage = () => {
  const exampleState = useSelector((state) => state.exampleState.value);
  const dispatch = useDispatch();

  return (
    <div>
      홈이에용
      <SmallButton />
      <button
        type="button"
        onClick={() => 
          dispatch(plusTest(2))
        }
      >
        뭐야이거
      </button>
      {exampleState}
    </div>
  );
};

export default MainPage;
