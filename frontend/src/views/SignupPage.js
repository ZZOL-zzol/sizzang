import Button from "../components/common/Button";
import { Link } from "react-router-dom";
import TextInput from "../components/common/TextInput";
import Dropdown from "../components/common/Dropdown";

const SignupPage = () => {
    return(
        <div className=" w-full h-full bg-white outline outline-1">
      <div className="flex w-full h-2/5 items-center justify-center">
        <div>
          <img src="./ZZang_logo_text.png" alt="logo"/>
        </div>
      </div>
      <div className="flex flex-col h-3/5 justify-evenly  items-center">
        <div className="flex flex-col gap-2 w-full px-9">
          <TextInput placeholder="ID" />
          <TextInput placeholder="PASSWORD" />
        </div>
        <div className="flex flex-col gap-2 w-full px-9">
          <TextInput placeholder="이름" />
          <TextInput placeholder="닉네임" />
        </div>
        <div className="flex flex-col gap-2 w-full px-9">
          <Dropdown/>
          <TextInput placeholder="닉네임" />
        </div>
        <div className="flex flex-col gap-2 w-full items-center px-9">
          <Link to={"/main"} className="w-full">
            <Button innerText='시작하기' color='bg-blue-200'/>
          </Link>
        </div>
      </div>
    </div>
    )
};

export default SignupPage;