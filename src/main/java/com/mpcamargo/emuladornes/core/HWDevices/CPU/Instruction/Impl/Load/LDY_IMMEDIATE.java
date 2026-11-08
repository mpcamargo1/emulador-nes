package com.mpcamargo.emuladornes.core.HWDevices.CPU.Instruction.Impl.Load;

import com.mpcamargo.emuladornes.constants.NESConstants;
import com.mpcamargo.emuladornes.core.HWDevices.CPU.CPU;
import com.mpcamargo.emuladornes.core.HWDevices.CPU.Flag.Flag;
import com.mpcamargo.emuladornes.core.HWDevices.CPU.Instruction.ExecutableInstruction;
import com.mpcamargo.emuladornes.core.HWDevices.CPU.Instruction.Parameters;

public class LDY_IMMEDIATE implements ExecutableInstruction {
    @Override
    public void execute(CPU cpu, Parameters parameters) throws Exception {
        int programCounter = cpu.getProgramCounter();
        int value = cpu.getBusHelper().read(programCounter);

        if (value == 0x00) {
            cpu.getFlagHelper().addFlag(Flag.ZERO);
        } else {
            cpu.getFlagHelper().removeFlag(Flag.ZERO);
        }

        if ((value & NESConstants.MASK_BIT_7) != 0) {
            cpu.getFlagHelper().addFlag(Flag.NEGATIVE);
        } else {
            cpu.getFlagHelper().removeFlag(Flag.NEGATIVE);
        }

        cpu.getRegisterHelper().updateY(value);
        cpu.setProgramCounter(++programCounter);
    }
}
