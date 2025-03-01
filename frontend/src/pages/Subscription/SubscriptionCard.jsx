import React from "react";
import { Button } from "@/components/ui/button";
import { CheckCircledIcon } from "@radix-ui/react-icons";
import { useDispatch } from "react-redux";
import { createPayment } from "../../Redux/Payment/Action";

export const SubscriptionCard = ({ data }) => {
  const dispatch = useDispatch();
  const token = localStorage.getItem("jwt")
  const handleUpgrade = () => {
    dispatch(
      createPayment({ subType: data.planType, jwt: token })
    );
  };
  return (
    <div className="rounded-xl bg-[#1B1C2A] bg-opacity-20 shadow-[#27293C] shadow-2xl card p-5 space-y-5 w-[18rem]">
      <p> {data.planName}</p>
      <p>
        <span className="text-xl font-semibold">${data.price}/</span>
        <span>{data.planType}</span>
      </p>

      <Button onClick={handleUpgrade} className="w-full">{data.buttonName}</Button>

      <div>
        {data.features.map((item) => (
          <div key={item} className="flex items-center gap-2">
            <CheckCircledIcon />
            <p>{item}</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default SubscriptionCard;
