
const TextInput = (props) => {
    return(
        <input type="text" placeholder={props.placeholder} className="input w-[270px] border-2 border-blue-100 focus:outline-blue-500" />
    )
};

export default TextInput;