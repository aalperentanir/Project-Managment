import React from "react";
import SubscriptionCard from "./SubscriptionCard";
import { useSelector } from "react-redux";

const freePlan = [
  "Add only 3 projects",
  "Basic task management features",
  "Limited storage",
  "Limited team members",
  "No support or community support only",
];

const annualyPlan = [
  "Add unlimited project",
  "Advanced reporting and analytics",
  "High storage",
  "Dedicated account manager & premium support",
  "Access tp live chat",
  "Extra security and backup features",
  "Unlimited team members",
];

const monthlyPlan = [
  "Add unlimited project",
  "Advanced task management",
  "Unlimited team members",
  "Medium storage ",
  "API access and integrations",
  "Priority customer support",
  "Dedicated account manager & premium support",
];

export const Subscription = () => {
  const {subscription} = useSelector(store => store);

  return (
    <div className="p-10">
      <h1 className="text-5xl font-semibold py-5 pb-16 text-center">
        Subscription Plans
      </h1>
      <div className="flex flex-col lg:flex-row justify-center items-center gap-9">
        <SubscriptionCard
          data={{
            planName: "Free",
            features: freePlan,
            planType: "FREE",
            price: 0,
            buttonName: subscription.usersSubscription?.subType == "FREE" ? "Current Plan" : "Get Started",
          }}
        />
        <SubscriptionCard
          data={{
            planName: "Monthly Plan",
            features: monthlyPlan,
            planType: "MONTHLY",
            price: 5,
            buttonName:  subscription.usersSubscription?.subType == "MONTHLY" ? "Current Plan" : "Get Started",
          }}
        />
        <SubscriptionCard
          data={{
            planName: "Annual Plan",
            features: annualyPlan,
            planType: "ANNUAL",
            price: 40,
            buttonName: subscription.usersSubscription?.subType == "ANNUAL" ? "Current Plan" : "Get Started",
          }}
        />
      </div>
    </div>
  );
};

export default Subscription;
